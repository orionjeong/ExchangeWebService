package kr.ac.jejunu.exchange.Controller;


import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Service.UserService;
import kr.ac.jejunu.exchange.Util.StateCode;
import lombok.AllArgsConstructor;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController(value = "")
@AllArgsConstructor
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session) throws SQLException, ClassNotFoundException {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication = authenticationManager.authenticate(token); //내가 커스텀한 provider로 인증 진행
        SecurityContextHolder.getContext().setAuthentication(authentication);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return new AuthenticationToken(username, authentication.getAuthorities() ,session.getId());

    }


}
