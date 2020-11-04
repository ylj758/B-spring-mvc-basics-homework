package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.LoginException;
import com.thoughtworks.capacity.gtb.mvc.exception.RegisterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    public void register(User user, List<User> userList) throws RegisterException {
        validateRegister(user, userList);
        user.setId(userList.size() + 1);
        userList.add(user);
    }

    public Optional<User> login(String username, String password, List<User> userList) throws LoginException {
       return validateLogin(username, password, userList);
    }

    private Optional<User> validateLogin(String username, String password, List<User> userList) throws LoginException {
        List<String> names = new ArrayList<>();
        for (User u : userList) {
            names.add(u.getUsername());
            if (u.getUsername().equals(username) && !u.getPassword().equals(password)) {
                throw new LoginException();
            }
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return Optional.of(u);
            }
        }
        if (!names.contains(username)) {
            throw new LoginException();
        }
        return null;
    }

    private void validateRegister(User user, List<User> userList) throws RegisterException {
        for (User u : userList) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new RegisterException();
            }
        }
    }
}
