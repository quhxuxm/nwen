package online.nwen.entry.controller;

import online.nwen.entry.common.ApiResponseGenerator;
import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAuthenticationService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.authenticate.AuthenticateDTO;
import online.nwen.service.dto.authenticate.AuthenticateResultDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationApiController {
    private IAuthenticationService authenticationService;

    public AuthenticationApiController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthenticateResultDTO> authenticate(@RequestBody ApiRequest<AuthenticateDTO> request)
            throws ServiceException {
        AuthenticateResultDTO authenticateResultDTO = this.authenticationService.authenticate(request.getPayload());
        return ApiResponseGenerator.INSTANCE.generate(authenticateResultDTO);
    }
}
