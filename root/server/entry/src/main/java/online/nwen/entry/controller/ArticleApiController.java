package online.nwen.entry.controller;

import online.nwen.entry.common.SecurityContextHolder;
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

    @PostMapping("/security/article/create")
    public ApiResponse<CreateArticleResultDTO> create(@RequestBody ApiRequest<CreateArticleDTO> createArticleRequest)
            throws ServiceException {
        if (SecurityContextHolder.INSTANCE.getContext().getCurrentAuthor() == null) {
            throw new ServiceException(ServiceException.Code.SYSTEM_ERROR);
        }
        CreateArticleDTO createArticleDTO = createArticleRequest.getPayload();
        if (createArticleDTO.getAnthologyId() == null) {
            createArticleDTO.setAnthologyId(
                    SecurityContextHolder.INSTANCE.getContext().getCurrentAuthor().getDefaultAnthologyId());
        }
        CreateArticleResultDTO resultPayload = this.articleService.createArticle(createArticleDTO,
                SecurityContextHolder.INSTANCE.getContext().getCurrentAuthor().getAuthorId());
        ApiResponse<CreateArticleResultDTO> result = new ApiResponse<>();
        result.setPayload(resultPayload);
        return result;
    }

    @PostMapping("/security/article/{id}/switchAnthology")
    public ApiResponse<ArticleSwitchAnthologyResultDTO> switchAnthology(@PathVariable("id") Long articleId,
                                                                        @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
