package online.nwen.entry.controller;

import online.nwen.entry.common.ApiResponseGenerator;
import online.nwen.entry.configuration.ApiConfiguration;
import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.RegisterAuthorDTO;
import online.nwen.service.dto.author.RegisterAuthorResultDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterApiController {
    private ApiConfiguration apiConfiguration;
    private IAuthorService authorService;

    public RegisterApiController(ApiConfiguration apiConfiguration,
                                 IAuthorService authorService) {
        this.apiConfiguration = apiConfiguration;
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<RegisterAuthorResultDTO> register(@RequestBody ApiRequest<RegisterAuthorDTO> request)
            throws ServiceException {
        this.verify(request);
        RegisterAuthorResultDTO responsePayload = this.authorService.register(request.getPayload());
        return ApiResponseGenerator.INSTANCE.generate(responsePayload);
    }

    private void verify(ApiRequest<RegisterAuthorDTO> request) throws ServiceException {
        RegisterAuthorDTO payload = request.getPayload();
        if (payload == null) {
            throw new ServiceException(ExceptionCode.REQUEST_PAYLOAD_EMPTY_ERROR);
        }
        if (null == payload.getUsername()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_USERNAME_IS_EMPTY);
        }
        if (null == payload.getPassword()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_PASSWORD_IS_EMPTY);
        }
        if (null == payload.getNickname()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_NICKNAME_IS_EMPTY);
        }
        if (!this.apiConfiguration.getUsernamePattern().matcher(request.getPayload().getUsername()).find()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_USERNAME_FORMAT_INCORRECT);
        }
        if (!this.apiConfiguration.getPasswordPattern().matcher(request.getPayload().getPassword()).find()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_PASSWORD_FORMAT_INCORRECT);
        }
        if (!this.apiConfiguration.getNicknamePattern().matcher(request.getPayload().getNickname()).find()) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_REGISTER_NICKNAME_FORMAT_INCORRECT);
        }
    }
}
