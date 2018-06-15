package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Comment;
import kr.ac.jejunu.exchange.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity create(@RequestBody Comment comment){

        //인가에 대한 처리를 안해줬기때문에 억지로 403에러 발생시키자
        //TODO 추후 인가에 대한 시큐리티 처리 필수
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName()=="anonymousUser"){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        comment.setUserId(authentication.getName());
        try{
            commentRepository.save(comment);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping
    public void update(@RequestBody Comment comment){
        commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id ){
        commentRepository.delete(commentRepository.findById(id).get());
    }

    @GetMapping("/list/search")
    public List<Comment> listByProductId(@RequestParam Integer productId){
        return commentRepository.findByProductId(productId);
    }
}
