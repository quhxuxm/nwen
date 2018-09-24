package online.nwen.entry.model.payload;

import online.nwen.service.api.exception.ExceptionCode;

public class ExceptionPayload {
    private ExceptionCode code;

    public ExceptionCode getCode() {
        return code;
    }

    public void setCode(ExceptionCode code) {
        this.code = code;
    }
}
