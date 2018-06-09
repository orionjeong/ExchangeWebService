package kr.ac.jejunu.exchange;


import kr.ac.jejunu.exchange.Model.Basket;
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
public class BasketTest {
    public static final String PATH="/api/basket";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){
        Integer basketId = 1;
        String userId = "user2";
        Integer productId = 1;

        Basket basket = restTemplate.getForObject(PATH+"/"+ basketId, Basket.class);
        assertThat(basket.getBasketId(), is(basketId));
        assertThat(basket.getUserId(), is(userId));
        assertThat(basket.getProductId(), is(productId));
    }
    @Test
    public void list(){
        List<Basket> baskets = restTemplate.getForObject(PATH +"/list", List.class);
        assertThat(baskets, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        String userId = "user2";
        Integer productId = 1;
        Basket createBasket = createBasket(userId, productId);
        validate(userId, productId,createBasket);

    }
    @Test
    public void update(){
        String userId = "user2";
        Integer productId = 1;
        Basket createBasket = createBasket(userId, productId);
        validate(userId, productId,createBasket);
        createBasket.setUserId("user5");
        restTemplate.put(PATH, createBasket);
        validate("user5", productId, createBasket);

    }

    @Test
    public void delete(){
        String userId = "user2";
        Integer productId = 1;
        Basket createBasket = createBasket(userId, productId);
        validate(userId, productId,createBasket);
        restTemplate.delete(PATH+"/"+createBasket.getBasketId());
        Basket deleteBasket = restTemplate.getForObject(PATH +"/"+createBasket.getBasketId(), Basket.class);
        assertThat(deleteBasket.getUserId(), is(nullValue()));
    }


    private Basket createBasket(String userId, Integer productId) {
        Basket basket = new Basket();
        basket.setUserId(userId);
        basket.setProductId(productId);
        return restTemplate.postForObject(PATH, basket, Basket.class);
    }

    private void validate(String userId, Integer productId, Basket createBasket) {
        Basket resultBasket = restTemplate.getForObject(PATH+"/"+createBasket.getBasketId(), Basket.class);
        assertThat(resultBasket.getUserId(), is(userId));
        assertThat(resultBasket.getProductId(), is(productId));
    }



}
