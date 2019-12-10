package com.github.bolatkhankadyrov;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.Constants;
import com.github.bolatkhankadyrov.helpers.models.CommentModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class BaseRestClient {
    protected static Response fetch(String endpoint, Map<String, String> params) {
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

    public static <T> List<T> ResponseToModels(Response response, T model) throws IOException {
        String listOsPostsByUserId = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
        };

        return objectMapper.readValue(listOsPostsByUserId, typeReference);
    }
}
