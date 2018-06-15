package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByCategoryLike(String category, Pageable pageable);
    List<Product> findAllByProvider(String provider);

}
