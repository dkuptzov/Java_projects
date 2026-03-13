package ru.tictactoe.web.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tictactoe.service.UserService;

import java.io.IOException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Component
public class AuthFilter extends GenericFilterBean {
    private final UserService userService;

    public AuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Пропускаем запросы на регистрацию и авторизацию
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/user/signup_json") ||
                path.startsWith("/user/auth_json") ||
                path.startsWith("/user/signup_base64") ||
                path.startsWith("/user/auth_base64")) {
            chain.doFilter(request, response);
            return;
        }

        // Получаем заголовок Authorization
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        try {
            // Декодируем Base64
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
            String[] parts = credentials.split(":", 2);

            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid credentials format");
            }

            String login = parts[0];
            String password = parts[1];

            // Валидация через UserService
            UUID userId = userService.validateUser(login, password);

            // Сохраняем userId и логин для дальнейшего использования в запросе
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userId, login,
                            List.of(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Если всё хорошо
            chain.doFilter(request, response);

        } catch (IllegalArgumentException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Base64 encoding");
        } catch (RuntimeException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
