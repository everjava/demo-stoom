package br.com.stoom.demo.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lng")
    private String longitude;

}
