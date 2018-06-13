package kr.ac.jejunu.exchange.Controller;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String username;
    String password;
}
