package online.nwen.entry.controller;

import online.nwen.entry.request.ApiRequest;
import online.nwen.entry.response.ApiResponse;
import online.nwen.entry.security.AuthenticatedAuthorDetailHolder;
import online.nwen.service.api.IArticleService;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/list/createdate",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listOrderByLastUpdate(
            @RequestParam(name = "pageindex", defaultValue = "0",
                    required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10",
                    required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByCreateDate(pageable, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/list/viewnumber",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listOrderByViewNumber(
            @RequestParam(name = "pageindex", defaultValue = "0",
                    required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10",
                    required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByViewNumber(pageable, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/list/praisenumber",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listOrderByPraiseNumber(
            @RequestParam(name = "pageindex", defaultValue = "0",
                    required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10",
                    required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByPraiseNumber(pageable, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/list/commentnumber",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listOrderByCommentNumber(
            @RequestParam(name = "pageindex", defaultValue = "0",
                    required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10",
                    required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByCommentNumber(pageable, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/list/bookmarknumber",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listOrderByBookmarkNumber(
            @RequestParam(name = "pageindex", defaultValue = "0",
                    required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", defaultValue = "10",
                    required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByBookmarkNumber(pageable, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/list/authorinterest",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listByAuthorInterest(
            @RequestParam(name = "pageindex", required = false,
                    defaultValue = "0")
                    Integer pageIndex,
            @RequestParam(name = "pagesize", required = false,
                    defaultValue = "10")
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        AuthorDetailDTO authenticatedAuthorDetail = AuthenticatedAuthorDetailHolder.INSTANCE
                .get();
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByAuthorInterests(pageable,
                        authenticatedAuthorDetail.getAuthorId(), 5, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/detail/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponse<ArticleDetailDTO> detail(
            @PathVariable(name = "id")
                    Long id) throws ServiceException {
        AuthorDetailDTO authenticatedAuthorDetail = AuthenticatedAuthorDetailHolder.INSTANCE
                .get();
        ArticleViewDTO articleViewDTO = new ArticleViewDTO();
        articleViewDTO.setArticleId(id);
        if (authenticatedAuthorDetail != null) {
            articleViewDTO.setAuthorId(authenticatedAuthorDetail.getAuthorId());
        }
        ArticleDetailDTO articleDetailDTO = this.articleService
                .viewArticle(articleViewDTO);
        ApiResponse<ArticleDetailDTO> result = new ApiResponse<>();
        result.setPayload(articleDetailDTO);
        return result;
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<Long> create(
            @RequestBody
                    ApiRequest<CreateArticleDTO> request)
            throws ServiceException {
        Long articleId = this.articleService.saveArticle(request.getPayload());
        ApiResponse<Long> result = new ApiResponse<>();
        result.setPayload(articleId);
        return result;
    }

    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<Long> update(
            @RequestBody
                    ApiRequest<UpdateArticleDTO> request)
            throws ServiceException {
        Long articleId = this.articleService
                .updateArticle(request.getPayload());
        ApiResponse<Long> result = new ApiResponse<>();
        result.setPayload(articleId);
        return result;
    }
}
