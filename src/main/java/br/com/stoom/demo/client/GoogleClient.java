package br.com.stoom.demo.client;


import br.com.stoom.demo.client.dto.GoogleResult;
import br.com.stoom.demo.client.dto.EndpointParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(value = "GoogleClient", url = "https://maps.googleapis.com/maps/api/geocode/json?")
public interface GoogleClient {

    @GetMapping()
    Optional<GoogleResult> callApi(@SpringQueryMap EndpointParameter endpointParameter);

}
