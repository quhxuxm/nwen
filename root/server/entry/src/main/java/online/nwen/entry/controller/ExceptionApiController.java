package online.nwen.entry.controller;

import online.nwen.entry.exception.ExceptionPayload;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.SecurityException;
import online.nwen.service.api.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionApiController {
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onServiceException(ServiceException e) {
        return generateExceptionPayloadApiResponse(e.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onException(Exception e) {
        return generateExceptionPayloadApiResponse(ExceptionCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = SecurityException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onSecurityException(SecurityException e) {
        return generateExceptionPayloadApiResponse(e.getCode());
    }

    private ApiResponse<ExceptionPayload> generateExceptionPayloadApiResponse(ExceptionCode code) {
        ExceptionPayload payload = new ExceptionPayload();
        payload.setCode(code);
        ApiResponse<ExceptionPayload> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setPayload(payload);
        return response;
    }
}
