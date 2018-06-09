package kr.ac.jejunu.exchange.Controller;

import kr.ac.jejunu.exchange.Model.Product;
import kr.ac.jejunu.exchange.Repository.ProductRepository;
import kr.ac.jejunu.exchange.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping(value = "/{id}")
    public Product get(@PathVariable Integer id){
        return productService.get(id);
    }

    @GetMapping(value = "/list")
    public List<Product> list(){
        return productService.list();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create( product);
    }

    @PutMapping
    public void update(@RequestBody Product product){
        productService.update(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id){
        productService.delete(id);
    }


}



