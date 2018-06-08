package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
