package com.github.bolatkhankadyrov.helpers.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.helpers.models.PostModel;
import com.github.bolatkhankadyrov.Constants;
import com.github.bolatkhankadyrov.BaseRestClient;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsUtils extends BaseRestClient {
    public static List<PostModel> getPostsByUserId(String userId) throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put("userId", userId);

        Response response = fetch(Constants.POSTS_PATH, param);

        String listOsPostsByUserId = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<PostModel>> typeReference = new TypeReference<List<PostModel>>() {
        };

        return objectMapper.readValue(listOsPostsByUserId, typeReference);
    }

    private static Response getByUserId(String id) {
        Map<String, String> param = new HashMap<>();
        param.put("userId", id);
        return fetch(Constants.POSTS_PATH, param);
    }

    public static List<PostModel> ResponseToPosts(Response response) throws IOException {
        String listOfPosts = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<PostModel>> typeReference = new TypeReference<List<PostModel>>() {
        };

        return objectMapper.readValue(listOfPosts, typeReference);
    }
}
