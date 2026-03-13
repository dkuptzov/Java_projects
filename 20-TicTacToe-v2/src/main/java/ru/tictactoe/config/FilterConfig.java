package ru.tictactoe.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tictactoe.web.filter.AuthFilter;
import ru.tictactoe.service.UserService;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(UserService userService) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter(userService));
        registrationBean.addUrlPatterns("/game/*", "/user/*", "/pvp/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}