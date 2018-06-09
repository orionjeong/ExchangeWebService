package kr.ac.jejunu.exchange.Service;

import kr.ac.jejunu.exchange.Model.Product;
import kr.ac.jejunu.exchange.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product get(Integer id) {
      return  productRepository.findById(id).get();
    }

    public List<Product> list() {
      return  productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.delete(productRepository.findById(id).get());
    }
}
