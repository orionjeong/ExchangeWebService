package kr.ac.jejunu.exchange;

import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Model.Product;
import kr.ac.jejunu.exchange.Repository.UserRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {
    public static final String PATH = "/api/product";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void get() {
        Integer id = 1;
        String title = "product1";
        String contents = "product1 contents";
        String image = "경로";
        Product product = restTemplate.getForObject(PATH + "/" + id, Product.class);
        assertThat(product.getTitle(), is(title));
        assertThat(product.getContents(), is(contents));
        assertThat(product.getImage(), is(image));
    }

    @Test
    public void list() {
        List<Product> products = restTemplate.getForObject(PATH + "/list", List.class);
        assertThat(products, not(IsEmptyCollection.empty()));
    }

    //user를 서버에서 넣기 때문에 인증안됬을 경우 anonymousUser를 가져오므로 이를  db에 넣고 test
    @Test
    public void listByProvider(){
        List<Product> products = restTemplate.getForObject(PATH+"/resistrationList", List.class);
        assertThat(products, not(IsEmptyCollection.empty()));
    }
    @Test
    public void create() {
        String title = "product";
        String contents = "product contents";
        String image = "경로";
        Product createProduct =createProduct(title, contents, image);
        validate(title, contents, image, createProduct);
    }
    @Test
    public void update(){
        String title = "product";
        String contents = "product contents";
        String image = "경로";
        Product createProduct = createProduct(title, contents, image);
        validate(title, contents, image, createProduct);
        createProduct.setTitle("title변경");
        restTemplate.put(PATH, createProduct, User.class);
        validate("title변경", contents, image, createProduct);
    }
    @Test
    public void delete(){
        String title = "product";
        String contents = "product contents";
        String image = "경로";
        Product createProduct = createProduct(title, contents, image);
        validate(title, contents, image, createProduct);
        restTemplate.delete(PATH+"/"+createProduct.getProductId());
        Product deleteProduct = restTemplate.getForObject(PATH + "/"+ createProduct.getProductId(), Product.class);
        assertThat(deleteProduct.getTitle(), is(nullValue()));
        assertThat(deleteProduct.getContents(), is(nullValue()));
    }
    private Product createProduct(String title, String contents, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setContents(contents);
        product.setImage(image);
        return restTemplate.postForObject(PATH, product, Product.class);
    }
    private void validate(String title, String contents, String image,Product createdProduct) {
        Product resultProduct = restTemplate.getForObject(PATH + "/" + createdProduct.getProductId(), Product.class);
        assertThat(resultProduct.getTitle(), is(title));
        assertThat(resultProduct.getContents(), is(contents));
        assertThat(resultProduct.getImage(), is(image));
    }
}
