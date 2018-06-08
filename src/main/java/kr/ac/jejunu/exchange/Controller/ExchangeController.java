package kr.ac.jejunu.exchange.Controller;


import kr.ac.jejunu.exchange.Model.Exchange;
import kr.ac.jejunu.exchange.Repository.ExchageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    @Autowired
    ExchageRepository exchageRepository;

    @GetMapping("/{id}")
    public Exchange get(){
        exchageRepository.fin
    }
}
