package online.nwen.entry.controller;

import online.nwen.entry.common.ApiResponseGenerator;
import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
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

    @GetMapping("/article/{id}/detail")
    public ApiResponse<GetArticleDetailResultDTO> detail(@PathVariable("id") String id) {
        GetArticleDetailDTO getArticleDetailDTO = new GetArticleDetailDTO();
        getArticleDetailDTO.setArticleId(id);
        GetArticleDetailResultDTO getArticleDetailResultDTO = this.articleService.getArticleDetail(getArticleDetailDTO);
        return ApiResponseGenerator.INSTANCE.generate(getArticleDetailResultDTO);
    }

    @GetMapping("/article/{id}/summary")
    public ApiResponse<GetArticleSummaryResultDTO> summary(@PathVariable("id") String id) {
        GetArticleSummaryDTO getArticleSummaryDTO = new GetArticleSummaryDTO();
        getArticleSummaryDTO.setArticleId(id);
        GetArticleSummaryResultDTO getArticleSummaryResultDTO =
                this.articleService.getArticleSummary(getArticleSummaryDTO);
        return ApiResponseGenerator.INSTANCE.generate(getArticleSummaryResultDTO);
    }

    @PostMapping("/security/article/delete")
    public ApiResponse<DeleteArticleResultDTO> delete(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping("/security/article/bookmark")
    public ApiResponse<BookmarkArticleResultDTO> bookmark(
            @RequestBody ApiRequest<BookmarkArticleDTO> bookmarkArticleApiRequest) {
        if (bookmarkArticleApiRequest.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        BookmarkArticleResultDTO bookmarkArticleResultDTO =
                this.articleService.bookmarkArticle(bookmarkArticleApiRequest.getPayload());
        return ApiResponseGenerator.INSTANCE.generate(bookmarkArticleResultDTO);
    }

    @PostMapping("/security/article/praise")
    public ApiResponse<PraiseArticleResultDTO> praise(
            @RequestBody ApiRequest<PraiseArticleDTO> praiseArticleApiRequest) {
        if (praiseArticleApiRequest.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        PraiseArticleResultDTO praiseArticleResultDTO =
                this.articleService.praiseArticle(praiseArticleApiRequest.getPayload());
        return ApiResponseGenerator.INSTANCE.generate(praiseArticleResultDTO);
    }

    @PostMapping("/security/article/save")
    public ApiResponse<SaveArticleResultDTO> save(@RequestBody ApiRequest<SaveArticleDTO> saveArticleApiRequest) {
        if (saveArticleApiRequest.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        SaveArticleDTO saveArticleDTO = saveArticleApiRequest.getPayload();
        SaveArticleResultDTO resultPayload = this.articleService.saveArticle(saveArticleDTO);
        return ApiResponseGenerator.INSTANCE.generate(resultPayload);
    }

    @PostMapping("/security/article/{id}/switchAnthology")
    public ApiResponse<ArticleSwitchAnthologyResultDTO> switchAnthology(@PathVariable("id") Long articleId,
                                                                        @RequestParam("anthologyId") Long anthologyId) {
        return null;
    }
}
