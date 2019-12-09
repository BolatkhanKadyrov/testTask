package com.github.bolatkhankadyrov.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestUtil {
    protected Response fetch(String endpoint, Map<String, String> params) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URL)
                .params(params)
                .get(endpoint);
    }

    public static String writeValueAsString(Object model) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(model);
    }
}
