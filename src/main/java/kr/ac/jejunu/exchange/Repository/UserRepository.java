package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String>{

}
