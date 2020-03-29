
package com.imdb.users.service.converters;

import com.imdb.users.model.UserModel;
import org.springframework.stereotype.Component;
import com.imdb.users.entities.User;

@Component
public class UserConverter {

    public UserModel convertToModel(final User user) {
        if (user == null) {
            return null;
        }

        final UserModel model = new UserModel();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());

        return model;
    }

    public User convertToEntity(final UserModel model) {
        if (model == null) {
            return null;
        }

        final User user = new User();
        user.setId(model.getId());
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());

        return user;
    }

}
