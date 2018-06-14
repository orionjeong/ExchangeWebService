package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    List<Basket> findAllByUsername(String username);
}
