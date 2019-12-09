package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostModel {
    private long userId;
    private long id;
    private String title;
    private String body;

    PostModel(
            long userId,
            long id,
            String title,
            String body
    ) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    PostModel() {
    }
}
