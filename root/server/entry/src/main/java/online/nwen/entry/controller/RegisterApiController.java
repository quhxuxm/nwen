package online.nwen.entry.controller;

import online.nwen.entry.configuration.ApiConfiguration;
import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAuthorService;
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
        ApiResponse<RegisterAuthorResultDTO> response = new ApiResponse<>();
        response.setPayload(responsePayload);
        return response;
    }

    private void verify(ApiRequest<RegisterAuthorDTO> request) throws ServiceException {
        RegisterAuthorDTO payload = request.getPayload();
        if (payload == null) {
            throw new ServiceException(ServiceException.Code.REQUEST_PAYLOAD_EMPTY_ERROR);
        }
        if (null == payload.getToken()) {
            throw new ServiceException(ServiceException.Code.REGISTER_TOKEN_IS_EMPTY_ERROR);
        }
        if (null == payload.getPassword()) {
            throw new ServiceException(ServiceException.Code.REGISTER_PASSWORD_IS_EMPTY_ERROR);
        }
        if (null == payload.getNickname()) {
            throw new ServiceException(ServiceException.Code.REGISTER_NICKNAME_IS_EMPTY_ERROR);
        }
        if (!this.apiConfiguration.getTokenPattern().matcher(request.getPayload().getToken()).find()) {
            throw new ServiceException(ServiceException.Code.REGISTER_TOKEN_FORMAT_INCORRECT);
        }
        if (!this.apiConfiguration.getPasswordPattern().matcher(request.getPayload().getPassword()).find()) {
            throw new ServiceException(ServiceException.Code.REGISTER_PASSWORD_FORMAT_INCORRECT);
        }
        if(payload.getNickname().length() > 40){
            throw new ServiceException(ServiceException.Code.REGISTER_NICKNAME_MAX_LENGTH_INCORRECT);
        }
        if(payload.getNickname().length() < 3){
            throw new ServiceException(ServiceException.Code.REGISTER_NICKNAME_MIN_LENGTH_INCORRECT);
        }
        if (!this.apiConfiguration.getNickNamePattern().matcher(request.getPayload().getNickname()).find()) {
            throw new ServiceException(ServiceException.Code.REGISTER_NICKNAME_FORMAT_INCORRECT);
        }
    }
}
