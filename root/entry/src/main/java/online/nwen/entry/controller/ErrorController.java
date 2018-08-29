package online.nwen.entry.controller;

import online.nwen.entry.exception.ApiException;
import online.nwen.entry.response.ApiResponse;
import online.nwen.entry.response.FailPayload;
import online.nwen.service.api.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ErrorController {
    @ExceptionHandler(value = {ApiException.class})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleApiError(ApiException e) {
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        result.setPayload(e.getPayload());
        return result;
    }

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleServiceError(ServiceException e) {
        FailPayload systemErrorPayload;
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        switch (e.getCode()) {
            case AUTHOR_TOKEN_EXIST:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.AUTHOR_ERROR__TOKEN_EXIST);
                break;
            case AUTHOR_NICK_NAME_EXIST:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.AUTHOR_ERROR__NICK_NAME_EXIST);
                break;
            case AUTHOR_NOT_EXIST_BY_ID:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.AUTHOR_ERROR__NOT_EXIST);
                break;
            case AUTHOR_NOT_EXIST_BY_TOKEN:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.AUTHOR_ERROR__NOT_EXIST);
                break;
            case PERSISTENCE_FAIL:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.UNKNOWN_ERROR__SYSTEM);
                break;
            default:
                systemErrorPayload = new FailPayload(
                        FailPayload.Type.UNKNOWN_ERROR__SYSTEM);
                break;
        }
        result.setPayload(systemErrorPayload);
        return result;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleUnknownError(ServiceException e) {
        FailPayload systemErrorPayload = new FailPayload(
                FailPayload.Type.UNKNOWN_ERROR__SYSTEM);
        systemErrorPayload.setMessage(e.getMessage());
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        result.setPayload(systemErrorPayload);
        return result;
    }
}
