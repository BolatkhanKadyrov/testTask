package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostModel {
    private long userId;
    private int id;
    private String title;
    private String body;

    PostModel(
            long userId,
            int id,
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

    @Override
    public String toString() {
        return "PostModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
