package com.github.bolatkhankadyrov.matchers;

import com.github.bolatkhankadyrov.helpers.models.PostModel;
import org.junit.Assert;

import java.util.List;

public class PostsMatcher {
    public static void postsHasCorrectUserId(List<PostModel> posts, Long userId) {
        for (PostModel post : posts) {
            Assert.assertEquals(
                    "Post with id:" + post.getId() + "has wrong userId",
                    userId,
                    post.getUserId()
            );
        }
    }
}
