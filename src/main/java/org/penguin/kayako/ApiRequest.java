package org.penguin.kayako;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.google.common.io.CharStreams;

public class ApiRequest {
    private final String apiSecret;
    private final String apiKey;
    private final String salt;
    private final String signature;
    
    private UriBuilder uri;
    
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
    }
    
    private ApiRequest(String apiKey, String apiSecret, String salt, String signature, UriBuilder uri) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.salt = salt;
        this.signature = signature;
        this.uri = uri;
    }
    
    public ApiRequest withPath(String path) {
        return new ApiRequest(apiKey, apiSecret, salt, signature, uri.queryPath(path));
    }
    
    public ApiResponse get() throws ApiRequestException {
        try {
            UriBuilder uriBuilder = uri.queryParam("apikey", apiKey)
                    .queryParam("salt", salt)
                    .queryParam("signature", signature);
            BufferedReader reader = new BufferedReader(new InputStreamReader(uriBuilder.toURL().openStream()));
            String content = CharStreams.toString(reader);
            return new ApiResponse(content);
        } catch (Exception e) {
            // Gotta catch em all
            throw new ApiRequestException(e);
        }
    }
    
    public ApiRequest setSalt(String salt) throws ApiRequestException {
        return new ApiRequest(apiKey, apiSecret, salt, generateSignature(apiSecret, salt), uri);
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
    
    public static class ApiRequestException extends Exception {
        private ApiRequestException(Throwable e) {
            super("An exception occurred attempting to create API request", e);
        }
    }
}
