package online.nwen.entry.exception;

import online.nwen.entry.response.FailPayload;

public class ApiException extends RuntimeException {
    private FailPayload payload;

    public ApiException(FailPayload payload) {
        this.payload = payload;
    }

    public ApiException(String message, FailPayload payload) {
        super(message);
        this.payload = payload;
    }

    public ApiException(String message, Throwable cause,
                        FailPayload payload) {
        super(message, cause);
        this.payload = payload;
    }

    public ApiException(Throwable cause, FailPayload payload) {
        super(cause);
        this.payload = payload;
    }

    public ApiException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace,
                        FailPayload payload) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.payload = payload;
    }

    public FailPayload getPayload() {
        return payload;
    }
}
