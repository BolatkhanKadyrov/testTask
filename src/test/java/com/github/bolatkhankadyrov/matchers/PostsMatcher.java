package com.github.bolatkhankadyrov.matchers;

import com.github.bolatkhankadyrov.helpers.models.PostModel;

import java.util.List;

public class PostsMatcher {
    public static void postsHasCorrectUserId(List<PostModel> posts, long userId){
        for (PostModel post : posts) {
            if (!post.getUserId().equals(userId)) {
                System.out.println("Post with id:" + post.getId() + "has wrong userId");
                throw new AssertionError();
            }
        }
    }
}
