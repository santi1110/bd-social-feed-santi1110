package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        return username.matches("[A-Z][a-zA-Z0-9]{3,}");
        //TODO: validate username begins with an uppercase letter, is at least 4 characters long, and only contains
        //letters, numbers, and underscores
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,}$");

        //TODO: validate password contains at least 8 characters, an uppercase, and a lowercase letter.
        //valid symbols include: !@#$%^&*
    }

    @Override
    public void validate(Object userData) {

        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) { user.setRole(Role.USER); }
    }
}
