package ru.tictactoe.service;

import ru.tictactoe.repository.UsersRepository;
import ru.tictactoe.repository.entity.Users;

import java.util.Base64;
import java.util.UUID;

public class AuthService {
    private final UsersRepository usersRepository;

    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UUID authBase64(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        byte[] decoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decoded); // "login:password"
        String[] parts = credentials.split(":", 2);
        String login = parts[0];
        String password = parts[1];
        Users user = usersRepository.findByUsernameAndPassword(login, password)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));;
        return user.getId();
    }
}
