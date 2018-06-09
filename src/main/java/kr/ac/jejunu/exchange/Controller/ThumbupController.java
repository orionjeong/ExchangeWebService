package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Thumbup;
import kr.ac.jejunu.exchange.Repository.ThumbupRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Thumbup create(@RequestBody Thumbup thumbup){
        return thumbupRepository.save(thumbup);
    }

    @PutMapping
    public void update(@RequestBody Thumbup thumbup){
       thumbupRepository.save(thumbup);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        thumbupRepository.delete(thumbupRepository.findById(id).get());
    }
}
