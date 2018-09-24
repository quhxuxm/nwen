package online.nwen.entry.controller;

import online.nwen.entry.model.ApiResponse;
import online.nwen.entry.model.ApiResponseGenerator;
import online.nwen.entry.model.payload.ExceptionPayload;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.SecurityException;
import online.nwen.service.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The controller advice used to handle the exception response.
 */
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionApiController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionApiController.class);

    /**
     * Handle the service exception.
     *
     * @param e A service exception.
     * @return Service exception api response.
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onServiceException(ServiceException e) {
        logger.debug("Service exception happen, convert exception to api response. Exceptions is:\n", e);
        return generateExceptionPayloadApiResponse(e.getCode());
    }

    /**
     * Handle the a unknown exception.
     *
     * @param e A service exception.
     * @return Service exception api response.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onException(Exception e) {
        logger.debug("Unknown exception happen, convert exception to api response. Exceptions is:\n", e);
        return generateExceptionPayloadApiResponse(ExceptionCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = SecurityException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onSecurityException(SecurityException e) {
        logger.debug("Security exception happen, convert exception to api response. Exceptions is:\n", e);
        return generateExceptionPayloadApiResponse(e.getCode());
    }

    private ApiResponse<ExceptionPayload> generateExceptionPayloadApiResponse(ExceptionCode code) {
        ExceptionPayload payload = new ExceptionPayload();
        payload.setCode(code);
        return ApiResponseGenerator.INSTANCE.fail(payload);
    }
}
