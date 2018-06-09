package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
}
