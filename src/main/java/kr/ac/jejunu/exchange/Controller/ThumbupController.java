package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Thumbup;
import kr.ac.jejunu.exchange.Repository.ThumbupRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/thumbup")
@RequiredArgsConstructor
public class ThumbupController {

    @Autowired
    ThumbupRepository thumbupRepository;

    @GetMapping("/{id}")
    public Thumbup get(@PathVariable Integer id){
        return  thumbupRepository.findById(id).get();
    }

    @GetMapping("/list")
    public List<Thumbup> list(){
        return thumbupRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Thumbup thumbup){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName()=="anonymousUser"){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
         }
        thumbup.setUsername(authentication.getName());
        try{
        thumbupRepository.save(thumbup);

        } catch(Exception e){
             e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
          return ResponseEntity.status(HttpStatus.OK).body(null);}



    @PutMapping
    public void update(@RequestBody Thumbup thumbup){
       thumbupRepository.save(thumbup);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        thumbupRepository.delete(thumbupRepository.findById(id).get());
    }

    @GetMapping("/resistrationList")
    public List<Thumbup> resistrationLIst(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return thumbupRepository.findAllByUsername(authentication.getName());
    }
}
