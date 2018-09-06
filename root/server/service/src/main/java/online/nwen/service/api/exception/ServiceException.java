package online.nwen.service.api.exception;

public class ServiceException extends Exception {
    public enum Code {
        //Common errors
        REQUEST_PAYLOAD_EMPTY_ERROR,
        SYSTEM_ERROR,
        NOT_OWNER_ERROR,
        //Register errors
        REGISTER_TOKEN_IS_EMPTY_ERROR,
        REGISTER_PASSWORD_IS_EMPTY_ERROR,
        REGISTER_NICKNAME_IS_EMPTY_ERROR,
        REGISTER_TOKEN_FORMAT_INCORRECT,
        REGISTER_PASSWORD_FORMAT_INCORRECT,
        REGISTER_NICKNAME_FORMAT_INCORRECT,
        REGISTER_TOKEN_EXIST_ERROR,
        REGISTER_NICKNAME_EXIST_ERROR,
        //Anthology
        NOT_PARTICIPANT_ERROR,
        //Authentication
        AUTHENTICATION_TOKEN_NOT_EXIST,
        AUTHENTICATION_PASSWORD_NOT_MATCH,
        //Author
        AUTHOR_NOT_EXIST_ERROR
    }

    private final Code code;

    public ServiceException(Code code) {
        super();
        this.code = code;
    }

    public ServiceException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, Code code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                            Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
