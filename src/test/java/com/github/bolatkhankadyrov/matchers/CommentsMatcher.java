package com.github.bolatkhankadyrov.matchers;

import com.github.bolatkhankadyrov.helpers.models.CommentModel;
import com.github.bolatkhankadyrov.helpers.models.PostModel;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class CommentsMatcher {
    public static void commentsInPostsHasCorrectEmail(List<CommentModel> comments, List<PostModel> posts) {
        ArrayList<String> errors = new ArrayList<>();
        for (PostModel actualPost : posts) {
            CommentModel commentModel = comments
                    .stream()
                    .filter(comment -> comment.getPostId() == actualPost.getId())
                    .findFirst()
                    .orElse(null);

            Assert.assertNotNull("Comment not found\n", commentModel);

            if (!CommonMatcher.isValidEmail(commentModel.getEmail()))
                errors.add("\ncomment with id: " + commentModel.getId()
                        + " in post with postId: " + actualPost.getId()
                        + " has not valid email:" + commentModel.getEmail());
        }

        Assert.assertEquals("errors:\n" + errors, 0, errors.size());
    }
}
