package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.entry.response.FailPayload;
import online.nwen.entry.security.AuthenticatedAuthorDetailHolder;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.AuthorDetailDTO;
import online.nwen.service.dto.AuthorRegisterDTO;
import online.nwen.service.impl.common.ICommonConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/register",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<Long> register(
            @RequestBody
                    ApiRequest<AuthorRegisterDTO> request)
            throws ServiceException {
        Long authorId = this.authorService.register(request.getPayload());
        ApiResponse<Long> result = new ApiResponse<>();
        result.setPayload(authorId);
        return result;
    }

    /**
     * Initialize the authenticate author detail, refresh the session and the thread local
     *
     * @param httpSession Http Session
     * @return The initialize response.
     * @throws ServiceException The service exception
     */
    @PostMapping(value = "/initialize",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<AuthorDetailDTO> initialize(HttpSession httpSession)
            throws ServiceException {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String token = user.getUsername();
        AuthorDetailDTO authorDetailDTO = this.authorService
                .loginByToken(token);
        httpSession.setAttribute(
                ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL,
                authorDetailDTO);
        AuthenticatedAuthorDetailHolder.INSTANCE.set(authorDetailDTO);
        ApiResponse<AuthorDetailDTO> result = new ApiResponse<>();
        result.setPayload(authorDetailDTO);
        return result;
    }

    /**
     * Return require authenticate error.
     *
     * @return The require authenticate error.
     */
    @GetMapping(value = "/authenticate")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse<FailPayload> authenticate() {
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        FailPayload authenticationRequiredPayload = new FailPayload(
                FailPayload.Type.AUTHENTICATION_ERROR__AUTHENTICATION_REQUIRED);
        result.setPayload(authenticationRequiredPayload);
        return result;
    }

    @RequestMapping(value = "/clear")
    @ResponseBody
    public ApiResponse<Void> clear(HttpSession session) {
        session.removeAttribute(
                ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL);
        ApiResponse<Void> result = new ApiResponse<>();
        result.setPayload(null);
        return result;
    }
}
