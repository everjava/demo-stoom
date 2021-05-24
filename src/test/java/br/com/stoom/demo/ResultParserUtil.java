package br.com.stoom.demo;

import br.com.stoom.demo.client.dto.GoogleResult;
import br.com.stoom.demo.client.dto.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultParserUtil {


    public static GoogleResult getResult() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(RESULT, GoogleResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final String RESULT = "{\n" +
            "   \"results\" : [\n" +
            "      {\n" +
            "         \"address_components\" : [\n" +
            "            {\n" +
            "               \"long_name\" : \"Rua Professor Ayrton Roberto de Oliveira\",\n" +
            "               \"short_name\" : \"R. Prof. Ayrton Roberto de Oliveira\",\n" +
            "               \"types\" : [ \"route\" ]\n" +
            "            },\n" +
            "            {\n" +
            "               \"long_name\" : \"Itacorubi\",\n" +
            "               \"short_name\" : \"Itacorubi\",\n" +
            "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\n" +
            "            },\n" +
            "            {\n" +
            "               \"long_name\" : \"Florianópolis\",\n" +
            "               \"short_name\" : \"Florianópolis\",\n" +
            "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n" +
            "            },\n" +
            "            {\n" +
            "               \"long_name\" : \"Santa Catarina\",\n" +
            "               \"short_name\" : \"SC\",\n" +
            "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
            "            },\n" +
            "            {\n" +
            "               \"long_name\" : \"Brazil\",\n" +
            "               \"short_name\" : \"BR\",\n" +
            "               \"types\" : [ \"country\", \"political\" ]\n" +
            "            },\n" +
            "            {\n" +
            "               \"long_name\" : \"88034\",\n" +
            "               \"short_name\" : \"88034\",\n" +
            "               \"types\" : [ \"postal_code\", \"postal_code_prefix\" ]\n" +
            "            }\n" +
            "         ],\n" +
            "         \"formatted_address\" : \"R. Prof. Ayrton Roberto de Oliveira - Itacorubi, Florianópolis - SC, 88034, Brazil\",\n" +
            "         \"geometry\" : {\n" +
            "            \"bounds\" : {\n" +
            "               \"northeast\" : {\n" +
            "                  \"lat\" : -27.5783117,\n" +
            "                  \"lng\" : -48.5094241\n" +
            "               },\n" +
            "               \"southwest\" : {\n" +
            "                  \"lat\" : -27.5789896,\n" +
            "                  \"lng\" : -48.5115338\n" +
            "               }\n" +
            "            },\n" +
            "            \"location\" : {\n" +
            "               \"lat\" : -27.5785352,\n" +
            "               \"lng\" : -48.5107344\n" +
            "            },\n" +
            "            \"location_type\" : \"GEOMETRIC_CENTER\",\n" +
            "            \"viewport\" : {\n" +
            "               \"northeast\" : {\n" +
            "                  \"lat\" : -27.5773016697085,\n" +
            "                  \"lng\" : -48.5091299697085\n" +
            "               },\n" +
            "               \"southwest\" : {\n" +
            "                  \"lat\" : -27.5799996302915,\n" +
            "                  \"lng\" : -48.5118279302915\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         \"partial_match\" : true,\n" +
            "         \"place_id\" : \"ChIJwVNwX_I4J5URXLqiFyhFXq0\",\n" +
            "         \"types\" : [ \"route\" ]\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}\n";
}
