package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.dto.article.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleApiController {
    @GetMapping("/{id}/detail")
    public ApiResponse<ArticleDetailDTO> detail(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}/summary")
    public ApiResponse<ArticleSummaryDTO> summary(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/{id}/update")
    public ApiResponse<UpdateArticleResultDTO> update(@PathVariable("id") Long id,
                                                      ApiRequest<UpdateArticleDTO> updateArticle) {
        return null;
    }

    @PostMapping("/{id}/delete")
    public ApiResponse<DeleteArticleResultDTO> delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public ApiResponse<CreateArticleResultDTO> create(ApiRequest<CreateArticleDTO> createArticle) {
        return null;
    }

    @PostMapping("/{id}/switchAnthology")
    public ApiResponse<ArticleSwitchAnthologyResultDTO> switchAnthology(@PathVariable("id") Long articleId,
                                                                        @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
