package online.nwen.service.api.exception;

public class ServiceException extends RuntimeException {
    private final ExceptionCode code;

    public ServiceException(ExceptionCode code) {
        super();
        this.code = code;
    }

    public ServiceException(String message, ExceptionCode code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, ExceptionCode code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause, ExceptionCode code) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                            ExceptionCode code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }
}
