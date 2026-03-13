package ru.tictactoe.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tictactoe.repository.entity.Users;
import ru.tictactoe.service.AuthService;
import ru.tictactoe.service.UserService;
import ru.tictactoe.web.model.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(
            UserService userService,
            AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup_json")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest request) {
        Users user = new Users(request.getUserName(), request.getPassword());
        UUID userId = userService.createUser(user);
        if (userId == null) {
            UserExistsResponse response = new UserExistsResponse("Username already exists!");
            return ResponseEntity.ok(response);
        }
        CreateUserResponse response = new CreateUserResponse(
                "User created successfully", userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup_base64")
    public ResponseEntity<?> createUser(@RequestHeader("Signup") String authHeader) {
        UUID userId = userService.signupBase64(authHeader);
        if (userId == null) {
            UserExistsResponse response = new UserExistsResponse("Username already exists!");
            return ResponseEntity.ok(response);
        }
        CreateUserResponse response = new CreateUserResponse(
                "User created successfully", userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth_json")
    public ResponseEntity<?> authUserJson(@RequestBody SignUpRequest request) {
        try {
            UUID userId = userService.validateUser(request.getUserName(), request.getPassword());
            CreateUserResponse response = new CreateUserResponse("User authenticated successfully", userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorAuthResponse response = new ErrorAuthResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/auth_base64")
    public ResponseEntity<?> authUser(@RequestHeader("Authorization") String authHeader) {
        try {
            UUID userId = authService.authBase64(authHeader);
            CreateUserResponse response = new CreateUserResponse("User authenticated successfully", userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorAuthResponse response = new ErrorAuthResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<?> findUser(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(userService.findUser(userId));
        } catch (RuntimeException e) {
            ErrorAuthResponse response = new ErrorAuthResponse("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
