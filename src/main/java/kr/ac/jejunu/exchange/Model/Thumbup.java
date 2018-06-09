package kr.ac.jejunu.exchange.Model;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Thumbup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer thumbupId;
    String userId;
    Integer productId;

}
