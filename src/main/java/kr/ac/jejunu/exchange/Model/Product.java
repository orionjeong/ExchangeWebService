package kr.ac.jejunu.exchange.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;
    String title;
    String contents;
    String image;
    String category;
    Integer views;
    Integer likes;
}
