package online.nwen.entry.controller;

import online.nwen.entry.common.ApiResponseGenerator;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IResourceService;
import online.nwen.service.dto.resources.SaveResourceDTO;
import online.nwen.service.dto.resources.SaveResourceResultDTO;
import org.springframework.web.bind.annotation.PostMapping;
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
            SaveResourceDTO saveResourceDTO) {
        SaveResourceResultDTO saveResourceResultDTO = this.resourceService.save(saveResourceDTO);
        return ApiResponseGenerator.INSTANCE.generate(saveResourceResultDTO);
    }
}
