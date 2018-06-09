package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Basket;
import kr.ac.jejunu.exchange.Repository.BasketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Basket create(@RequestBody Basket basket){
        return basketRepository.save(basket);
    }

    @PutMapping
    public void update(@RequestBody Basket basket){
        basketRepository.save(basket);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        basketRepository.delete(basketRepository.findById(id).get());
    }
}
