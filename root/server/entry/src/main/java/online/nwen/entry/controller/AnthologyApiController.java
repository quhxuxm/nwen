package online.nwen.entry.controller;

import online.nwen.entry.common.ApiResponseGenerator;
import online.nwen.entry.response.ApiResponse;
import online.nwen.service.api.IAnthologyService;
import online.nwen.service.dto.anthology.GetAnthologyDetailDTO;
import online.nwen.service.dto.anthology.GetAnthologyDetailResultDTO;
import online.nwen.service.dto.anthology.SaveAnthologyDTO;
import online.nwen.service.dto.anthology.SaveAnthologyResultDTO;
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
        return ApiResponseGenerator.INSTANCE.generate(getAnthologyDetailResultDTO);
    }

    @PostMapping("/security/anthology/save")
    public ApiResponse<SaveAnthologyResultDTO> update(
            SaveAnthologyDTO saveAnthologyDTO) {
        SaveAnthologyResultDTO saveAnthologyResultDTO = this.anthologyService.save(saveAnthologyDTO);
        return ApiResponseGenerator.INSTANCE.generate(saveAnthologyResultDTO);
    }
}
