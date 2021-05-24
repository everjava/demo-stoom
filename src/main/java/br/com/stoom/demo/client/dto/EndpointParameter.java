package br.com.stoom.demo.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EndpointParameter {
    private String address;
    private String key;
}
