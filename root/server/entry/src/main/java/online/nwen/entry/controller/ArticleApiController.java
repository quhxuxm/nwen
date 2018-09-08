package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.article.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleApiController {
    private IArticleService articleService;

    public ArticleApiController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/{id}/detail")
    public ApiResponse<ArticleDetailDTO> detail(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/article/{id}/summary")
    public ApiResponse<ArticleSummaryDTO> summary(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/security/article/{id}/update")
    public ApiResponse<UpdateArticleResultDTO> update(@PathVariable("id") Long id,
                                                      ApiRequest<UpdateArticleDTO> updateArticle) {
        return null;
    }

    @PostMapping("/security/article/{id}/delete")
    public ApiResponse<DeleteArticleResultDTO> delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/security/article/save")
    public ApiResponse<SaveArticleResultDTO> save(ApiRequest<SaveArticleDTO> saveArticleRequest)
            throws ServiceException {
        Long articleId = this.articleService.saveArticle(saveArticleRequest.getPayload());
        SaveArticleResultDTO resultPayload = new SaveArticleResultDTO();
        resultPayload.setArticleId(articleId);
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
