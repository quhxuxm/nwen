package online.nwen.service.api.exception;

public enum ExceptionCode {
    //Common errors
    REQUEST_PAYLOAD_EMPTY_ERROR,
    SYSTEM_ERROR,
    NOT_OWNER_ERROR,
    //Register errors
    REGISTER_ERROR_USERNAME_EXIST,
    REGISTER_ERROR_NICKNAME_EXIST,
    //Authentication
    AUTHENTICATION_TOKEN_NOT_EXIST,
    AUTHENTICATION_PASSWORD_NOT_MATCH,
    //Author
    AUTHOR_ERROR_NOT_EXIST,
    //Anthology
    ANTHOLOGY_ERROR_NOT_EXIST,
    ANTHOLOGY_ERROR_AUTHOR_NEITHER_OWNER_NOR_PARTICIPANT,
    //Article
    ARTICLE_ERROR_NOT_EXIST,
    ARTICLE_ERROR_AUTHOR_NOT_OWNER,
    //Input
    INPUT_ERROR_EMPTY_ARTICLE_TITLE,
    INPUT_ERROR_EMPTY_ANTHOLOGY_ID,
    INPUT_ERROR_EMPTY_AUTHOR_ID,
    INPUT_ERROR_EMPTY_ARTICLE_ID,
    INPUT_ERROR_REGISTER_USERNAME_IS_EMPTY,
    INPUT_ERROR_REGISTER_PASSWORD_IS_EMPTY,
    INPUT_ERROR_REGISTER_NICKNAME_IS_EMPTY,
    INPUT_ERROR_REGISTER_USERNAME_FORMAT_INCORRECT,
    INPUT_ERROR_REGISTER_PASSWORD_FORMAT_INCORRECT,
    INPUT_ERROR_REGISTER_NICKNAME_FORMAT_INCORRECT,
    //Security
    SECURITY_ERROR_CONTEXT_NOT_FOUND,
    SECURITY_ERROR_AUTHOR_NOT_FOUND_IN_CONTEXT,
    SECURITY_ERROR_JWT_TOKEN_INVALID
}
