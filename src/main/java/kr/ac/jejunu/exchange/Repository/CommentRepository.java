package kr.ac.jejunu.exchange.Repository;

import kr.ac.jejunu.exchange.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
