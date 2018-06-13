package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByProductId(Integer productId);
}
