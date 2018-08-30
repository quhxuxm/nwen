package online.nwen.entry.controller;

import online.nwen.service.dto.anthology.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anthology")
public class AnthologyApiController {
    @GetMapping("/{id}/detail")
    public AnthologyDetailDTO detail(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}/summary")
    public AnthologySummaryDTO summary(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/{id}/update")
    public UpdateAnthologyResultDTO update(@PathVariable("id") Long id, UpdateAnthologyDTO updateAnthologyDTO) {
        return null;
    }

    @PostMapping("/{id}/delete")
    public DeleteAnthologyResultDTO delete(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/create")
    public CreateAnthologyResultDTO create(CreateAnthologyDTO createAnthologyDTO) {
        return null;
    }
}
