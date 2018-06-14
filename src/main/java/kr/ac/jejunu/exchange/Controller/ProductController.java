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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public ResponseEntity create(@RequestBody Product product){
        log.info("-----------------"+product);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        product.setProvider(authentication.getName());
        try{
            productRepository.save(product);
        }catch(Exception e){
            e.printStackTrace();
           return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
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

    @PostMapping("/image")
    public ResponseEntity createImage(@RequestParam("file")MultipartFile image) {
        log.info("_______________________________"+ image);
        String filename = image.getOriginalFilename();
        String path = System.getProperty("user.dir") + "/out/production/resources/static/productImage/";
        new File(path).mkdirs();
      try{
          image.transferTo(new File(path  + filename));
      }catch(IOException e){
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body(null);
      }
      return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}



