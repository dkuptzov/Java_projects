package ru.tictactoe.service;

import ru.tictactoe.repository.UsersRepository;
import ru.tictactoe.repository.entity.Users;

import java.util.Base64;
import java.util.UUID;

public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UUID createUser(Users user) {
        if (usersRepository.existsByUsername(user.getUsername())) {
            return null;
        }
        return usersRepository.save(user).getId();
    }

    public UUID validateUser(String login, String password) {
        Users user = usersRepository.findByUsernameAndPassword(login, password)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        return user.getId();
    }

    public UUID signupBase64(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        byte[] decoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decoded); // "login:password"
        String[] parts = credentials.split(":", 2);
        String login = parts[0];
        String password = parts[1];
        Users user = new Users(login, password);
        createUser(user);
        return user.getId();
    }

    public String findUser(UUID userId) {
        return usersRepository.findById(userId).stream().map(Users::getUsername).toList().getFirst();
    }
}
