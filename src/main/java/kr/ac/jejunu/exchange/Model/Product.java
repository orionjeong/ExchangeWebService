package kr.ac.jejunu.exchange.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    String title;
    String contents;
    String image;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    List<Exchange> exchanges;




}
