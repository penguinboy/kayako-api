package org.penguin.kayako;

/**
 * Base wrapper for API calls.
 *
 * @author fatroom
 */
public abstract class AbstractConnector {
    private final KayakoClient client;

    protected AbstractConnector(KayakoClient client) {
        this.client = client;
    }

    protected KayakoClient getClient() {
        return client;
    }
}
