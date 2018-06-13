package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
