package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Basket;
import kr.ac.jejunu.exchange.Model.Thumbup;
import kr.ac.jejunu.exchange.Repository.BasketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@AllArgsConstructor
public class BasketController {

    @Autowired
    BasketRepository basketRepository;

    @GetMapping("/{id}")
    public Basket get(@PathVariable Integer id ){

        return basketRepository.findById(id).get();
    }

    @GetMapping("/list")
    public List<Basket> list(){
        return basketRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Basket basket){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName()=="anonymousUser"){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        basket.setUsername(authentication.getName());
        try{
            basketRepository.save(basket);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);


    }

    @PutMapping
    public void update(@RequestBody Basket basket){
        basketRepository.save(basket);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        basketRepository.delete(basketRepository.findById(id).get());
    }

    @GetMapping("/resistrationList")
    public List<Basket> resistrationLIst(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return basketRepository.findAllByUsername(authentication.getName());
    }
}
