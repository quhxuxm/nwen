package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.author.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorApiController {
    private IAuthorService authorService;

    private AuthorApiController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}/detail")
    public ApiResponse<AuthorDetailDTO> detail(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public ApiResponse<CreateAuthorResultDTO> create(ApiRequest<CreateAuthorDTO> createAuthorRequest)
            throws ServiceException {
        if (null == createAuthorDTO.getToken()) {
            ServiceException e = new ServiceException();
            e.getPayload().put("token", "empty");
            throw e;
        }
        if (null == createAuthorDTO.getPassword()) {
            throw new ServiceException();
        }
        if (null == createAuthorDTO.getNickName()) {
            throw new ServiceException();
        }
        return this.authorService.register(createAuthorDTO);
    }

    @PostMapping("/{id}/update")
    public ApiResponse<UpdateAuthorResultDTO> update(@PathVariable("id") Long id,
                                                     ApiRequest<UpdateAuthorDTO> updateAuthor) {
        return null;
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthorAuthenticationDTO> authenticate() {
        return null;
    }
}
