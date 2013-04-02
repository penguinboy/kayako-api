package org.penguin.kayako;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.net.URLEncoder.encode;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

public class UriBuilder {
    private final static String UTF8 = "UTF-8";
    
    private final String host;
    private final String path;
    private final String queryPath;
    private final String scheme = "http";
    private final ImmutableMap<String, String> queryParameters;
    
    public UriBuilder(String host) {
        this.host = host;
        this.path = "";
        this.queryPath = "";
        this.queryParameters = ImmutableMap.<String, String> builder().build();;
    }
    
    private UriBuilder(String host, String path, String queryPath, Map<String, String> queryParameters) {
        this.host = host;
        this.path = path;
        this.queryPath = queryPath;
        this.queryParameters = ImmutableMap.<String, String> copyOf(queryParameters);
    }
    
    public UriBuilder path(String pathSegment) throws UriBuilderException {
        try {
            String newPath = path + "/" + encode(trimSlashes(pathSegment), UTF8);
            return new UriBuilder(host, newPath, queryPath, queryParameters);
        } catch (UnsupportedEncodingException e) {
            throw new UriBuilderException(e);
        }
    }
    
    public UriBuilder queryPath(String pathSegment) throws UriBuilderException {
        try {
            String newQueryPath = queryPath + "/" + encode(trimSlashes(pathSegment), UTF8);
            return new UriBuilder(host, path, newQueryPath, queryParameters);
        } catch (UnsupportedEncodingException e) {
            throw new UriBuilderException(e);
        }
    }
    
    public UriBuilder queryParam(String key, String value) {
        return new UriBuilder(host, path, queryPath,
                ImmutableMap.<String, String> builder().putAll(queryParameters).put(key, value).build());
    }
    
    public String getPath() throws URISyntaxException {
        try {
            String basePath = path + (!isNullOrEmpty(queryPath) || queryParameters.size() > 0 ? "?" + queryPath : "");
            int i = 0;
            for (Entry<String, String> entry : queryParameters.entrySet()) {
                if (i != 0 || !isNullOrEmpty(queryPath)) {
                    basePath = basePath + "&";
                }
                basePath = basePath + encode(entry.getKey(), UTF8) + "=" + encode(entry.getValue(), UTF8);
                
                i++;
            }
            return basePath;
        } catch (UnsupportedEncodingException e) {
            throw new UriBuilderException(e);
        }
    }
    
    public URL toURL() {
        try {
            return new URL(scheme, host, getPath());
        } catch (Exception e) {
            throw new UriBuilderException(e);
        }
    }
    
    private static String trimSlashes(String path) {
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        while (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }
    
    public static class UriBuilderException extends RuntimeException {
        public UriBuilderException(Throwable e) {
            super("An error occurred building URI", e);
        }
    }
}
