package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchageRepository extends JpaRepository<Exchange, Integer> {
    List<Exchange> findByProductId(Integer productId);
}
