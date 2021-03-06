package kr.ac.jejunu.exchange.Model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Data
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int exchangeId;
    String title;
    String contents;
    String image;
    String userId;
    int productId;
}
