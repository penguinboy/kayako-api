package org.penguin.kayako;

import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class ApiRequest {
    private final String apiSecret;
    private String salt;
    private String signature;

    public ApiRequest(String apiSecret) throws ApiRequestException {
        this.apiSecret = apiSecret;

        SecureRandom r = new SecureRandom();
        StringBuilder saltBuilder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            saltBuilder.append((char) (97 + r.nextInt(26)));
        }
        this.salt = saltBuilder.toString();

        this.generateSignature();
    }

    public void setSalt(String salt) throws ApiRequestException {
        this.salt = salt;
        generateSignature();
    }

    private void generateSignature() throws ApiRequestException {
        Mac hmac;
        try {
            hmac = Mac.getInstance("HmacSHA256");
            hmac.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));

            this.signature = new BASE64Encoder().encode(hmac.doFinal(this.salt.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new ApiRequestException(e);
        }
    }

    public String getSignature() {
        return signature;
    }

    public static class ApiRequestException extends Exception {
        public ApiRequestException(Throwable e) {
            super("An exception occurred attempting to create API request", e);
        }
    }
}
