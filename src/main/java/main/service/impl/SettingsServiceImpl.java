package main.service.impl;

import main.api.response.SettingsResponse;
import main.repository.GlobalSettingsRepository;
import main.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;

    @Override
    public SettingsResponse getGlobalSettings() {

        SettingsResponse settingsResponse = new SettingsResponse();
        settingsResponse.setMultiuseMode(globalSettingsRepository.findAllGlobalSettings("MULTIUSER_MODE").getValue().equals("YES"));

        settingsResponse.setPostPremoderation(globalSettingsRepository.findAllGlobalSettings("POST_PREMODERATION").getValue().equals("YES"));
        settingsResponse.setStatisticIsPublic(globalSettingsRepository.findAllGlobalSettings("STATISTICS_IS_PUBLIC").getValue().equals("YES"));
        return settingsResponse;
    }
}
