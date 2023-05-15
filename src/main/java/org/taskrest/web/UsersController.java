package org.taskrest.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.taskrest.user.UserDao;
import org.taskrest.user.UserService;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/users/{login}")
    public UserDao getUser(@PathVariable String login){
        return userService.getUser(login);
    }
}
