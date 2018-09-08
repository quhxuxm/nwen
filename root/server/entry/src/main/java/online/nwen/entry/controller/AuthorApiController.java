package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAuthorService;
import online.nwen.service.dto.author.AuthorDetailDTO;
import online.nwen.service.dto.author.UpdateAuthorDTO;
import online.nwen.service.dto.author.UpdateAuthorResultDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorApiController {
    private IAuthorService authorService;

    private AuthorApiController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}/detail")
    public ApiResponse<AuthorDetailDTO> detail(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/security/author/{id}/update")
    public ApiResponse<UpdateAuthorResultDTO> update(@PathVariable("id") Long id,
                                                     ApiRequest<UpdateAuthorDTO> updateAuthor) {
        return null;
    }
}
