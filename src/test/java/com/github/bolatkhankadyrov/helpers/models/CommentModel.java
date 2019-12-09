package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentModel {
    private long postId;
    private long id;
    private String name;
    private String email;
    private String body;

    CommentModel(
            long postId,
            long id,
            String name,
            String email,
            String body
    ) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    CommentModel() {
    }
}
