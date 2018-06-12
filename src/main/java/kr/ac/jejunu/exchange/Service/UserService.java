package kr.ac.jejunu.exchange.Service;

import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //로그인 인증(암호화된 비밀번호 가져와서 확인)
    public UsernamePasswordAuthenticationToken login(String username, String password) throws SQLException, ClassNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        if(bCryptPasswordEncoder.matches(password, user.getPassword()) == false) return null;
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //ADMIN이면 세개 다 USER면 두개 다 가지도록 BRAKE생략
        switch (user.getType()) {
            case 0:
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case 1:
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            case 2:
                grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_NO"));
        }
        return new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), grantedAuthorityList);
    }

}
