package online.nwen.entry.controller;

import online.nwen.service.dto.*;
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
    public ArticleUpdateResultDTO update(@PathVariable("id") Long id, ArticleUpdateDTO articleUpdate) {
        return null;
    }

    @PostMapping("/{id}/delete")
    public ArticleDeleteResultDTO delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public ArticleCreateResultDTO create(ArticleCreateDTO articleCreation) {
        return null;
    }

    @PostMapping("/{id}/switchAnthology")
    public ArticleSwitchAnthologyResultDTO switchAnthology(@PathVariable("id") Long articleId,
                                                           @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
