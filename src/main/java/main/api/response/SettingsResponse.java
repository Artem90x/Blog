package main.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SettingsResponse {

    @JsonProperty("MULTIUSER_MODE")
    private boolean multiuseMode;
    @JsonProperty("POST_PREMODERATION")
    private boolean postPremoderation;
    @JsonProperty("STATISTIC_IS_PUBLIC")
    private boolean statisticIsPublic;
}
