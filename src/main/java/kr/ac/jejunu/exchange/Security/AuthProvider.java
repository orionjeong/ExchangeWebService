package kr.ac.jejunu.exchange.Security;

import kr.ac.jejunu.exchange.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.sql.SQLException;

public class AuthProvider implements AuthenticationProvider{
    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            return  userService.login(id, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
