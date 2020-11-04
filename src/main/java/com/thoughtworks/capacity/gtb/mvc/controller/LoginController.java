package com.thoughtworks.capacity.gtb.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.LoginException;
import com.thoughtworks.capacity.gtb.mvc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class LoginController {
    private final LoginService loginService;
    private List<User> userList = new ArrayList();

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody User user) throws Exception {
        loginService.register(user, userList);
    }


    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestParam(required = true)
                      @Size(max = 10, min = 3)
                      @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$")
                              String username,
                      @Size(max = 12, min = 5)
                      @RequestParam(required = true) String password) throws LoginException {
        Optional<User> user = loginService.login(username, password, userList);
        return user.get();
    }
}
