package kr.ac.jejunu.exchange.Controller;


import kr.ac.jejunu.exchange.Model.Exchange;
import kr.ac.jejunu.exchange.Repository.ExchageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Exchange create(@RequestBody Exchange exchange){
      return exchangeRepository.save(exchange);
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
