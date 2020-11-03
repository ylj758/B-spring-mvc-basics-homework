package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.RegisterException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    public void register(User user, List<User> userList) throws RegisterException {
        validate(user, userList);
        userList.add(user);
    }

    private void validate(User user, List<User> userList) throws RegisterException {
        for (User u : userList) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new RegisterException();
            }
        }
    }
}
