package org.penguin.kayako;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpRequestBase;

public interface HttpRequestExecutor {
    String execute(HttpRequestBase request) throws ParseException, IOException;
}
