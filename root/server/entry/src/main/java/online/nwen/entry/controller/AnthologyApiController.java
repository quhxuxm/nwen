package online.nwen.entry.controller;

import online.nwen.entry.response.ApiResponse;
import online.nwen.service.dto.anthology.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anthology")
public class AnthologyApiController {
    @GetMapping("/{id}/detail")
    public ApiResponse<AnthologyDetailDTO> detail(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}/summary")
    public ApiResponse<AnthologySummaryDTO> summary(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/{id}/update")
    public ApiResponse<UpdateAnthologyResultDTO> update(@PathVariable("id") Long id,
                                                        UpdateAnthologyDTO updateAnthologyDTO) {
        return null;
    }

    @PostMapping("/{id}/delete")
    public ApiResponse<DeleteAnthologyResultDTO> delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public ApiResponse<CreateAnthologyResultDTO> create(CreateAnthologyDTO createAnthologyDTO) {
        return null;
    }
}
