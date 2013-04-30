package org.penguin.kayako;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.penguin.kayako.exception.ApiRequestException;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

public class ApiRequest {
    private final String apiSecret;
    private final String apiKey;
    private final String salt;
    private final String signature;

    private UriBuilder uri;
    private List<NameValuePair> params;

    protected ApiRequest(KayakoClient client) throws ApiRequestException {
        this(client.getApiKey(), client.getApiSecret(), client.getBaseURI());
    }

    protected ApiRequest(String apiKey, String apiSecret, UriBuilder baseURI) throws ApiRequestException {
        this.apiSecret = apiSecret;

        SecureRandom r = new SecureRandom();
        StringBuilder saltBuilder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            saltBuilder.append((char) (97 + r.nextInt(26)));
        }
        this.apiKey = apiKey;
        this.salt = saltBuilder.toString();
        this.signature = generateSignature(apiSecret, salt);
        this.uri = baseURI;
        this.params = Lists.newArrayList();
    }

    private ApiRequest(String apiKey, String apiSecret, String salt, String signature, UriBuilder uri, List<NameValuePair> params) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.salt = salt;
        this.signature = signature;
        this.uri = uri;
        this.params = Lists.newArrayList(params);
    }

    public ApiRequest withPath(String path) {
        return new ApiRequest(apiKey, apiSecret, salt, signature, uri.queryPath(path), params);
    }

    public ApiRequest withPathRaw(String path) {
        return new ApiRequest(apiKey, apiSecret, salt, signature, uri.queryPathUnescaped(path), params);
    }

    public ApiRequest withPostParam(final String name, final String value) {
        ApiRequest request = new ApiRequest(apiKey, apiSecret, salt, signature, uri, params);
        request.params.add(new BasicNameValuePair(name, value));
        return request;
    }

    public ApiResponse get() throws ApiRequestException {
        try {
            UriBuilder uriBuilder = applySecurityParams(uri);
            String content = executeRequest(new HttpGet(uriBuilder.toURI()));
            return new ApiResponse(content);
        } catch (ClientProtocolException e) {
            throw new ApiRequestException(e);
        } catch (IOException e) {
            throw new ApiRequestException(e);
        }
    }

    public ApiResponse post() throws ApiRequestException {
        try {
            HttpPost post = new HttpPost(uri.toURI());
            List<NameValuePair> requestParams = applySecurityParams(params);
            post.setEntity(new UrlEncodedFormEntity(requestParams));
            String content = executeRequest(post);
            return new ApiResponse(content);
        } catch (IOException e) {
            throw new ApiRequestException(e);
        } catch (ApiRequestException e) {
            throw new ApiRequestException(e);
        }
    }

    public ApiResponse put() throws ApiRequestException {
        try {
            UriBuilder uriBuilder = applySecurityParams(uri);
            HttpPut put = new HttpPut(uriBuilder.toURI());
            List<NameValuePair> requestParams = applySecurityParams(params);
            put.setEntity(new UrlEncodedFormEntity(requestParams));
            String content = executeRequest(put);
            return new ApiResponse(content);
        } catch (ClientProtocolException e) {
            throw new ApiRequestException(e);
        } catch (IOException e) {
            throw new ApiRequestException(e);
        }
    }

    public ApiResponse delete() throws ApiRequestException {
        try {
            UriBuilder uriBuilder = applySecurityParams(uri);
            String content = executeRequest(new HttpDelete(uriBuilder.toURI()));
            return new ApiResponse(content);
        } catch (ClientProtocolException e) {
            throw new ApiRequestException(e);
        } catch (IOException e) {
            throw new ApiRequestException(e);
        }
    }

    public ApiRequest setSalt(String salt) throws ApiRequestException {
        return new ApiRequest(apiKey, apiSecret, salt, generateSignature(apiSecret, salt), uri, params);
    }

    public String getSignature() {
        return signature;
    }

    private static String generateSignature(String apiSecret, String salt) throws ApiRequestException {
        Mac hmac;
        try {
            hmac = Mac.getInstance("HmacSHA256");
            hmac.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));

            return new BASE64Encoder().encode(hmac.doFinal(salt.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new ApiRequestException(e);
        }
    }

    private String executeRequest(final HttpRequestBase request) throws IOException, ApiRequestException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        StatusLine status = response.getStatusLine();
        if (HttpStatus.SC_OK != status.getStatusCode()) {
            throw new ApiRequestException(new IOException("Request failed with status code: "+status.getStatusCode()));
        }
        return EntityUtils.toString(response.getEntity());
    }

    private UriBuilder applySecurityParams(final UriBuilder builder) {
        return builder.queryParam("apikey", apiKey)
                .queryParam("salt", salt)
                .queryParam("signature", signature);
    }

    private List<NameValuePair> applySecurityParams(final List<NameValuePair> originalParams) {
        List<NameValuePair> params = Lists.newArrayList(originalParams);
        params.add(new BasicNameValuePair("apikey", apiKey));
        params.add(new BasicNameValuePair("salt", salt));
        params.add(new BasicNameValuePair("signature", signature));
        return params;
    }
}
