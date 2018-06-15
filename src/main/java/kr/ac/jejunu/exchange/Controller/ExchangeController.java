package kr.ac.jejunu.exchange.Controller;


import kr.ac.jejunu.exchange.Model.Exchange;
import kr.ac.jejunu.exchange.Repository.ExchageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    @Autowired
    ExchageRepository exchangeRepository;

    @GetMapping("/{id}")
    public Exchange get(@PathVariable Integer id ){
        return exchangeRepository.findById(id).get();
    }

    @GetMapping("/list")
    public List<Exchange> list(){
        return exchangeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Exchange exchange){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        exchange.setUserId(authentication.getName());
        try{
            exchangeRepository.save(exchange);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping
    public void update(@RequestBody Exchange exchange){
        exchangeRepository.save(exchange);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        exchangeRepository.delete(exchangeRepository.findById(id).get());
    }


    @GetMapping("/list/search")
    public List<Exchange> listByProductId(@RequestParam Integer productId){
        return exchangeRepository.findByProductId(productId);
    }
}
