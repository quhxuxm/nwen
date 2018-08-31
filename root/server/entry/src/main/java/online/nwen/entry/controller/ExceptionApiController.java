package online.nwen.entry.controller;

import online.nwen.entry.exception.ExceptionPayload;
import online.nwen.entry.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionApiController {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse<ExceptionPayload> onException(Exception e) {
        ExceptionPayload payload = new ExceptionPayload();
        ApiResponse<ExceptionPayload> response = new ApiResponse<>();
        response.setPayload(payload);
        return response;
    }
}
