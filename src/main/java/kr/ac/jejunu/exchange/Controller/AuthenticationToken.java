package kr.ac.jejunu.exchange.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class AuthenticationToken {
    String username;
    java.util.Collection<? extends GrantedAuthority> authorities;
    String token;
}
