package main.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class CalendarResponse {

    @JsonProperty("years")
    private Set<String> years;

    @JsonProperty("posts")
    private Map<String, Integer> posts;

    public CalendarResponse(Set<String> years, Map<String, Integer> posts) {
        this.years = years;
        this.posts = posts;
    }
}
