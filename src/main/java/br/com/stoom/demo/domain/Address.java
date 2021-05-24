package br.com.stoom.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document("address")
@Data
@NoArgsConstructor
public class Address {

    @JsonProperty("_id")
    private String id;

    @NotBlank(message = "Campo obrigatório")
    private String streetName;

    @NotNull(message = "Campo obrigatório")
    private Integer number;

    private String complement;

    @NotBlank(message = "Campo obrigatório")
    private String neighbourhood;

    @NotBlank(message = "Campo obrigatório")
    private String city;

    @NotBlank(message = "Campo obrigatório")
    private String state;

    @NotBlank(message = "Campo obrigatório")
    private String country;

    @NotBlank(message = "Campo obrigatório")
    private String zipcode;

    private String latitude;

    private String longitude;


}
