package br.com.stoom.demo.service;

import br.com.stoom.demo.client.GoogleClient;
import br.com.stoom.demo.client.dto.EndpointParameter;
import br.com.stoom.demo.client.dto.Result;
import br.com.stoom.demo.domain.Address;
import br.com.stoom.demo.exception.CoordinateNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
public class CoordinatesService {

    private GoogleClient googleClient;

    @Value("${google.api.key}")
    private String apiKey;

    public CoordinatesService(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public void checkCoordinates(Address address) {
        if (isLatitudeLongitudeNotExists(address)) {
            callApi(address).ifPresent(m -> {
                address.setLongitude(m.get("lng"));
                address.setLatitude(m.get("lat"));
            });
        }
    }

    Optional<Map<String, String>> callApi(Address address) {
        StringBuilder addressAttributes = this.getAddressAttributes(address);
        try {
            EndpointParameter endpointParameter = EndpointParameter.builder()
                    .address(addressAttributes.toString())
                    .key(apiKey)
                    .build();

            List<Result> results = googleClient.callApi(endpointParameter)
                    .orElseThrow(CoordinateNotFound::new)
                    .getResults();

            return Optional.of(this.getCoordinates(results));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    Map<String, String> getCoordinates(List<Result> results) {
        Map<String, String> map = results.stream()
                .map(g -> g.getGeometry())
                .map(l -> l.getLocation())
                .map(h -> {
                    Map<String, String> m = new HashMap();
                    m.put("lat", h.getLatitude());
                    m.put("lng", h.getLongitude());
                    return m;
                }).findFirst().get();
        return map;
    }

    boolean isLatitudeLongitudeNotExists(Address address) {
        Predicate<Address> latitude = lat -> lat.getLatitude() == null;
        Predicate<Address> longitude = lng -> lng.getLongitude() == null;
        return latitude.and(longitude).test(address);
    }

    Predicate<Address> isLatitudeLongitudeNotExists2() {
        Predicate<Address> latitude = lat -> lat.getLatitude() == null;
        Predicate<Address> longitude = lng -> lng.getLongitude() == null;
        return latitude.and(longitude);
    }

    StringBuilder getAddressAttributes(Address address) {
        StringBuilder sb = new StringBuilder();
        sb.append(address.getNumber());
        sb.append("%20");
        sb.append(address.getStreetName());
        sb.append("%20");
        sb.append(address.getNeighbourhood());
        sb.append("%20");
        sb.append(address.getCity());
        sb.append("%20");
        sb.append(address.getState());
        sb.append("%20");
        sb.append(address.getCountry());
        sb.append("%20");
        sb.append(address.getZipcode());
        return sb;
    }

}
