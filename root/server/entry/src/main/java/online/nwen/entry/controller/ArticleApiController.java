package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IArticleService;
import online.nwen.service.dto.article.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleApiController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleApiController.class);
    private IArticleService articleService;

    public ArticleApiController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/{id}/view")
    public ApiResponse<ArticleDetailDTO> view(@PathVariable("id") String id) {
        return null;
    }

    @GetMapping("/article/{id}/summary")
    public ApiResponse<ArticleSummaryDTO> summary(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping("/security/article/{id}/delete")
    public ApiResponse<DeleteArticleResultDTO> delete(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping("/security/article/save")
    public ApiResponse<SaveArticleResultDTO> save(@RequestBody ApiRequest<SaveArticleDTO> saveArticleApiRequest) {
        SaveArticleDTO saveArticleDTO = saveArticleApiRequest.getPayload();
        SaveArticleResultDTO resultPayload = this.articleService.saveArticle(saveArticleDTO);
        ApiResponse<SaveArticleResultDTO> result = new ApiResponse<>();
        result.setPayload(resultPayload);
        return result;
    }

    @PostMapping("/security/article/{id}/switchAnthology")
    public ApiResponse<ArticleSwitchAnthologyResultDTO> switchAnthology(@PathVariable("id") Long articleId,
                                                                        @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
