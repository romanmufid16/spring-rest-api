package romanmufid.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import romanmufid.rest_api.entity.User;
import romanmufid.rest_api.model.RegisterUserRequest;
import romanmufid.rest_api.model.UpdateUserRequest;
import romanmufid.rest_api.model.UserResponse;
import romanmufid.rest_api.model.WebResponse;
import romanmufid.rest_api.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(path = "/api/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> get(User user) {
        UserResponse userResponse = userService.getUser(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(path = "/api/users/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.updateUser(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
