package club.luckylight.service;

import club.luckylight.model.User;

public interface AuthService {

    User login(String username, String password);

    User info(String username);
}
