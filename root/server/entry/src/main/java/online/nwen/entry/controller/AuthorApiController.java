package online.nwen.entry.controller;

import online.nwen.service.dto.author.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorApiController {
    @GetMapping("/{id}/detail")
    public AuthorDetailDTO detail(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public CreateAuthorResultDTO create(CreateAuthorDTO createAuthorDTO) {
        return null;
    }

    @PostMapping("/{id}/update")
    public UpdateAuthorResultDTO update(UpdateAuthorDTO updateAuthorDTO) {
        return null;
    }

    @PostMapping("/authenticate")
    public AuthorAuthenticationDTO authenticate() {
        return null;
    }
}
