package org.penguin.kayako;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.penguin.kayako.exception.ApiRequestException;

public class HttpRequestExecutorImpl implements HttpRequestExecutor {
    
    @Override
    public String execute(HttpRequestBase request) throws ParseException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        StatusLine status = response.getStatusLine();
        if (HttpStatus.SC_OK != status.getStatusCode()) {
            throw new ApiRequestException(new IOException("Request failed with status code: " + status.getStatusCode()));
        }
        return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
    }
    
}
