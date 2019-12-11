package com.github.bolatkhankadyrov.matchers;

import com.github.bolatkhankadyrov.helpers.models.UserModel;
import org.junit.Assert;

import java.util.ArrayList;

public class UsersMatcher {
    public static void correctUser(UserModel expectedUser, UserModel actualUser) {
        ArrayList<String> errors = new ArrayList<>();

        if (!expectedUser.getId().equals(actualUser.getId())) {
            errors.add("\nField id:\n\tExpected:\t" + expectedUser.getId() + "\n\tActual:\t" + actualUser.getId());
        }

        if (!expectedUser.getName().equals(actualUser.getName())) {
            errors.add("\nField name:\n\tExpected:\t" + expectedUser.getName() + "\n\tActual:\t" + actualUser.getName());
        }

        if (!expectedUser.getUsername().equals(actualUser.getUsername())) {
            errors.add("\nField username:\n\tExpected:\t" + expectedUser.getUsername() + "\n\tActual:\t" + actualUser.getUsername());
        }

        Assert.assertEquals("ResultDialog has errors:\n" + errors, 0, errors.size());
    }
}
