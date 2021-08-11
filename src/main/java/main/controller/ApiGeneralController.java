package main.controller;

import lombok.AllArgsConstructor;
import main.api.response.InitResponse;
import main.api.response.SettingsResponse;
import main.api.response.TagsResponse;
import main.service.impl.SettingsServiceImpl;
import main.service.impl.TagServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiGeneralController {
    private final InitResponse initResponse;
    private final SettingsServiceImpl settingsService;
    private final TagServiceImpl tagServiceImpl;


    @GetMapping("/init")
    private InitResponse init() {
        return initResponse;
    }

    @GetMapping("/settings")
    private SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/tag")
    private TagsResponse getTags(
            @RequestParam(required = false, defaultValue = "") String query) {
        return tagServiceImpl.getTags(query);

    }
}
