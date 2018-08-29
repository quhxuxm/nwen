package online.nwen.service.api.exception;

public class ServiceException extends Exception {
    public enum Code {
        AUTHOR_TOKEN_EXIST,
        AUTHOR_NICK_NAME_EXIST,
        AUTHOR_NOT_EXIST_BY_ID,
        AUTHOR_NOT_EXIST_BY_TOKEN,
        AUTHOR_NOT_PARTICIPANT_OF_ANTHOLOGY,
        AUTHOR_NOT_OWNER_OF_ARTICLE,
        AUTHOR_NOT_OWNER_OF_ANTHOLOGY,
        PERSISTENCE_FAIL
    }

    private final Code code;

    public ServiceException(Code code) {
        this.code = code;
    }

    public ServiceException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public ServiceException(Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, Code code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace, Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
