package online.nwen.service.api.exception;

public class SecurityException extends RuntimeException {
    public enum Code {
        //Security
        SECURITY_ERROR_CONTEXT_FAIL_TO_INITIALIZE,
        SECURITY_ERROR_CONTEXT_NOT_FOUND,
        SECURITY_ERROR_JWT_TOKEN_NOT_FOUND,
        SECURITY_ERROR_JWT_TOKEN_INVALID
    }

    private final Code code;

    public SecurityException(Code code) {
        super();
        this.code = code;
    }

    public SecurityException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public SecurityException(String message, Throwable cause, Code code) {
        super(message, cause);
        this.code = code;
    }

    public SecurityException(Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    public SecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                             Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
