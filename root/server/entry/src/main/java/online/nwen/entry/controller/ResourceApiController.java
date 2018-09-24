package online.nwen.entry.controller;

import online.nwen.entry.model.ApiResponseGenerator;
import online.nwen.entry.model.ApiRequest;
import online.nwen.entry.model.ApiResponse;
import online.nwen.service.api.IResourceService;
import online.nwen.service.api.exception.ExceptionCode;
import online.nwen.service.api.exception.ServiceException;
import online.nwen.service.dto.resources.SaveResourceDTO;
import online.nwen.service.dto.resources.SaveResourceResultDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceApiController {
    private IResourceService resourceService;

    public ResourceApiController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/security/resource/save")
    public ApiResponse<SaveResourceResultDTO> save(
            @RequestBody ApiRequest<SaveResourceDTO> saveResourceDTO) {
        if (saveResourceDTO.getPayload() == null) {
            throw new ServiceException(ExceptionCode.INPUT_ERROR_EMPTY_API_REQUEST_PAYLOAD);
        }
        SaveResourceResultDTO saveResourceResultDTO = this.resourceService.save(saveResourceDTO.getPayload());
        return ApiResponseGenerator.INSTANCE.success(saveResourceResultDTO);
    }
}
