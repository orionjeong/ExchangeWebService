package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Comment;
import kr.ac.jejunu.exchange.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/{id}")
    public Comment get(@PathVariable Integer id){
        return commentRepository.findById(id).get();
    }

    @GetMapping("/list")
    public List<Comment> list(){
        return commentRepository.findAll();
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @PutMapping
    public void update(@RequestBody Comment comment){
        commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id ){
        commentRepository.delete(commentRepository.findById(id).get());
    }
}
