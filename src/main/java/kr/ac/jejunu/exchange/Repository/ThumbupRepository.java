package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Thumbup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThumbupRepository extends JpaRepository<Thumbup, Integer> {
    List<Thumbup> findAllByUsername(String username);
}
