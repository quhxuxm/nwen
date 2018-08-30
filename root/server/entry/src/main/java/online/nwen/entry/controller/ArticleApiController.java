package online.nwen.entry.controller;

import online.nwen.service.dto.article.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {
    @GetMapping("/{id}/detail")
    public ArticleDetailDTO detail(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}/summary")
    public ArticleSummaryDTO summary(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/{id}/update")
    public UpdateArticleResultDTO update(@PathVariable("id") Long id, UpdateArticleDTO updateArticleDTO) {
        return null;
    }

    @PostMapping("/{id}/delete")
    public DeleteArticleResultDTO delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public CreateArticleResultDTO create(CreateArticleDTO createArticleDTO) {
        return null;
    }

    @PostMapping("/{id}/switchAnthology")
    public ArticleSwitchAnthologyResultDTO switchAnthology(@PathVariable("id") Long articleId,
                                                           @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
