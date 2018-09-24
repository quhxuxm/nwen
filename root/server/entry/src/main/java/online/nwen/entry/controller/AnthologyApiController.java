package online.nwen.entry.controller;

import online.nwen.entry.model.ApiResponseGenerator;
import online.nwen.entry.model.ApiRequest;
import online.nwen.entry.model.ApiResponse;
import online.nwen.service.api.IAnthologyService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.anthology.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AnthologyApiController {
    private IAnthologyService anthologyService;

    public AnthologyApiController(IAnthologyService anthologyService) {
        this.anthologyService = anthologyService;
    }

    @GetMapping("/anthology/{id}/detail")
    public ApiResponse<GetAnthologyDetailResultDTO> detail(@PathVariable("id") String id) {
        GetAnthologyDetailDTO getAuthorDetailDTO = new GetAnthologyDetailDTO();
        getAuthorDetailDTO.setAnthologyId(id);
        GetAnthologyDetailResultDTO getAnthologyDetailResultDTO =
                this.anthologyService.getAnthologyDetail(getAuthorDetailDTO);
        return ApiResponseGenerator.INSTANCE.success(getAnthologyDetailResultDTO);
    }

    @PostMapping("/security/anthology/bookmark")
    public ApiResponse<BookmarkAnthologyResultDTO> bookmark(
            @RequestBody ApiRequest<BookmarkAnthologyDTO> bookmarkAnthologyDTOApiRequest) {
        if (bookmarkAnthologyDTOApiRequest.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        BookmarkAnthologyResultDTO bookmarkAnthologyResultDTO =
                this.anthologyService.bookmarkAnthology(bookmarkAnthologyDTOApiRequest.getPayload());
        return ApiResponseGenerator.INSTANCE.success(bookmarkAnthologyResultDTO);
    }

    @PostMapping("/security/anthology/praise")
    public ApiResponse<PraiseAnthologyResultDTO> praise(
            @RequestBody ApiRequest<PraiseAnthologyDTO> praiseAnthologyDTOApiRequest) {
        if (praiseAnthologyDTOApiRequest.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        PraiseAnthologyResultDTO praiseAnthologyResultDTO =
                this.anthologyService.praiseAnthology(praiseAnthologyDTOApiRequest.getPayload());
        return ApiResponseGenerator.INSTANCE.success(praiseAnthologyResultDTO);
    }

    @PostMapping("/security/anthology/save")
    public ApiResponse<SaveAnthologyResultDTO> update(
            @RequestBody ApiRequest<SaveAnthologyDTO> saveAnthologyDTO) {
        if (saveAnthologyDTO.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        SaveAnthologyResultDTO saveAnthologyResultDTO = this.anthologyService.save(saveAnthologyDTO.getPayload());
        return ApiResponseGenerator.INSTANCE.success(saveAnthologyResultDTO);
    }
}
