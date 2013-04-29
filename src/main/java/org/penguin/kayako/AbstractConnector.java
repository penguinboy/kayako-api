package org.penguin.kayako;

import org.penguin.kayako.exception.ApiRequestException;

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

    protected ApiRequest getApiRequest() {
        return new ApiRequest(client);
    }

    protected static abstract class AbstractRequest {
        /**
         * Validate current request state.
         * Throws {@link ApiRequestException} in case request isn't ready to be passed to Kayako system.
         *
         * @throws ApiRequestException in case of some required parameters missing
         */
        protected abstract void validate() throws ApiRequestException;

        protected void checkNotNull(final Object value) throws ApiRequestException {
            if (value == null) {
                throw new ApiRequestException(new IllegalArgumentException("Argument can not be null"));
            }
        }
    }
}
