package br.com.stoom.demo.service;

import br.com.stoom.demo.ResultParserUtil;
import br.com.stoom.demo.domain.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class CoordinatesServiceTest {

    private Address address;
    @Autowired
    private CoordinatesService coordinatesService;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setZipcode("88034-050");
        address.setStreetName("Rua Prof. Ayrton Roberto de Oliveira");
        address.setState("SC");
        address.setNumber(151);
        address.setNeighbourhood("Itacorubi");
        address.setCountry("Brasil");
        address.setCity("Florianópolis ");
    }

    @Test
    void getAddressAttributes() {
        StringBuilder sb = coordinatesService.getAddressAttributes(address);
        assertTrue(sb.toString().indexOf("%20") > -1);
        assertTrue(sb.toString().lastIndexOf("%20") > -1);
    }

    @Test
    void isLatitudeLongitudeNotExists() {
        boolean notExists = coordinatesService.isLatitudeLongitudeNotExists(address);
        assertTrue(notExists);
    }

    @Test
    void getCoordinates() {
        Map<String, String> results = coordinatesService.getCoordinates(ResultParserUtil.getResult().getResults());
        assertFalse(results.isEmpty());
    }


}