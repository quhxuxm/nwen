package online.nwen.service.api.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceException extends Exception {
    private final Map<String, String> payload;

    public ServiceException() {
        super();
        this.payload = new HashMap<>();
    }

    public ServiceException(String message) {
        super(message);
        this.payload = new HashMap<>();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.payload = new HashMap<>();
    }

    public ServiceException(Throwable cause) {
        super(cause);
        this.payload = new HashMap<>();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.payload = new HashMap<>();
    }

    public Map<String, String> getPayload() {
        return payload;
    }
}
