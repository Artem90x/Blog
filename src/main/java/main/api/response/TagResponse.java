package main.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagResponse {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("weight")
    private final double weight;
}
