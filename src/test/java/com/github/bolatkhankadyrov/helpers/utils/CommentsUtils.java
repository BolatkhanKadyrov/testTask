package com.github.bolatkhankadyrov.helpers.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.Constants;
import com.github.bolatkhankadyrov.BaseRestClient;
import com.github.bolatkhankadyrov.helpers.models.CommentModel;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.bolatkhankadyrov.Constants.POST_ID;

public class CommentsUtils extends BaseRestClient {
    public Response getCommentsByPostID(String ID) {
        Map<String, String> param = new HashMap<>();
        param.put(POST_ID, ID);
        return fetch(Constants.COMMENTS_PATH, param);
    }

    public static List<CommentModel> responseToComments(Response response) throws IOException {
        String listOfComments = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<CommentModel>> typeReference = new TypeReference<List<CommentModel>>() {
        };

        return objectMapper.readValue(listOfComments, typeReference);
    }
}
