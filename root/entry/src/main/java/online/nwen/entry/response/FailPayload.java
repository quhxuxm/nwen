package online.nwen.entry.response;

public class FailPayload {
    public enum Type {
        AUTHENTICATION_ERROR__AUTHENTICATION_REQUIRED,
        AUTHENTICATION_ERROR__TOKEN_NOT_EXIST,
        AUTHOR_ERROR__TOKEN_EXIST,
        AUTHOR_ERROR__NICK_NAME_EXIST,
        UNKNOWN_ERROR__SERVICE,
        UNKNOWN_ERROR__SYSTEM,
        ARTICLE_ERROR__NOT_EXIST,
        AUTHOR_ERROR__NOT_EXIST
    }

    private String message;
    private Type type;

    public FailPayload(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
