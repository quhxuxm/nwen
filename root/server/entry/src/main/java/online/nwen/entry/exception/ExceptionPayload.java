package online.nwen.entry.exception;

import online.nwen.service.api.exception.ServiceException;

public class ExceptionPayload {
    private ServiceException.Code code;

    public ServiceException.Code getCode() {
        return code;
    }

    public void setCode(ServiceException.Code code) {
        this.code = code;
    }
}
