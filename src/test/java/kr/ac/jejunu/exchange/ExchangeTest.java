package kr.ac.jejunu.exchange;

import kr.ac.jejunu.exchange.Model.Exchange;
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
public class ExchangeTest {
    public static final String PATH="/api/exchange";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void get(){
        Integer exchangeId=1;
        String title = "교환";
        String contents = "교환상품입니다";
        String image="경로";
        String userId = "user2";
        Integer productId = 1;

        Exchange exchange = restTemplate.getForObject(PATH + "/" + exchangeId, Exchange.class);

        assertThat(exchange.getExchangeId(), is(exchangeId));
        validate(title, contents, image, userId, productId, exchange);
    }

    @Test
    public void list(){
        List<Exchange> exchanges = restTemplate.getForObject(PATH+"/list", List.class);
        assertThat(exchanges, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        String title = "교환";
        String contents = "교환상품입니다";
        String image="경로";
        String userId = "user2";
        Integer productId = 1;

        Exchange createExchage =createExchange(title, contents, image, userId, productId);
        validate(title, contents, image, userId, productId, createExchage);
    }

    @Test
    public void update(){
        String title = "교환";
        String contents = "교환상품입니다";
        String image="경로";
        String userId = "user2";
        Integer productId = 1;
        Exchange createExchage = createExchange(title, contents, image, userId, productId);
        validate(title, contents, image, userId, productId, createExchage);
        createExchage.setTitle("제목변경");
        restTemplate.put(PATH , createExchage);
        validate("제목변경", contents, image, userId, productId, createExchage);
    }

    @Test
    public void delete(){
        String title = "교환";
        String contents = "교환상품입니다";
        String image="경로";
        String userId = "user2";
        Integer productId = 1;
        Exchange createExchange = createExchange(title, contents, image, userId, productId);
        validate(title, contents, image, userId, productId, createExchange);
        restTemplate.delete(PATH+"/"+createExchange.getExchangeId());
        Exchange deleteExchange = restTemplate.getForObject(PATH+"/"+createExchange.getExchangeId(), Exchange.class);
        assertThat(deleteExchange.getTitle(), is(nullValue()));
        assertThat(deleteExchange.getContents(), is(nullValue()));
    }

    private Exchange createExchange(String title, String contents, String image, String userId, Integer productId) {
        Exchange exchange  =new Exchange();
        exchange.setTitle(title);
        exchange.setContents(contents);
        exchange.setImage(image);
        exchange.setUserId(userId);
        exchange.setProductId(productId);
        return restTemplate.postForObject(PATH, exchange, Exchange.class);
    }

    private void validate(String title, String contents, String image, String userId, Integer productId, Exchange createExchage) {
        Exchange resultExchange = restTemplate.getForObject(PATH+"/"+createExchage.getExchangeId(), Exchange.class);
        assertThat(resultExchange.getTitle(), is(title));
        assertThat(resultExchange.getContents(), is(contents));
        assertThat(resultExchange.getImage(), is(image));
        assertThat(resultExchange.getUserId(), is(userId));
        assertThat(resultExchange.getProductId(), is(productId));
    }
}
