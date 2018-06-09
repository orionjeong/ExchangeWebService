package kr.ac.jejunu.exchange.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @Column(name="userId",nullable=false)
    String userId;
    String name;
    String password;
    String email;
    String phone;
}
