package kr.ac.jejunu.exchange.Controller;

import javafx.scene.control.Pagination;
import kr.ac.jejunu.exchange.Model.Product;
import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @GetMapping(value = "/{id}")
    public Product get(@PathVariable Integer id){
        return  productRepository.findById(id).get();
    }

    @GetMapping(value = "/list")
    public List<Product> list(){
        return  productRepository.findAll();    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping
    public void update(@RequestBody Product product){
        productRepository.save(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id){
        productRepository.delete(productRepository.findById(id).get());
    }

    @GetMapping(value = "/list/search")
    public Page<Product> pagingList(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "의류") String category,
                                    @RequestParam(defaultValue = "productId") String filter)
    {
        PageRequest pageRequest = PageRequest.of(page-1, 25, Sort.Direction.DESC, filter);
        return productRepository.findAllByCategoryLike(category, pageRequest);
    }


    @GetMapping("/resistrationList")
    public List<Product> resistrationList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return productRepository.findAllByProvider(authentication.getName());
    }
}



