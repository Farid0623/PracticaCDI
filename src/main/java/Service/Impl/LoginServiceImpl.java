package Service.Impl;

import Service.LoginService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.util.Optional;

@ApplicationScoped
@Named("login")
public class LoginServiceImpl implements LoginService {
    private Connection conn;

    public LoginServiceImpl() {
        this.conn = conn;
    }

    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String  username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}

