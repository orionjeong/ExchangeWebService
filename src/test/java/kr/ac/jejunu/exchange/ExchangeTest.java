package kr.ac.jejunu.exchange;

import kr.ac.jejunu.exchange.Model.Exchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeTest {
    public static final String PATH="/api/exchage";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void get(){
        Integer exchangeId=1;
        String title = "교환";
        String contents = "교환상품입니다.";
        String image="경로";
        String userId = "user2";
        Integer productId = 1;

        Exchange exchange = restTemplate.getForObject(PATH + "/" + exchangeId, Exchange.class);

        assertThat(exchange.getExchange_id(), is(exchangeId));
        assertThat(exchange.getTitle(), is(title));
        assertThat(exchange.getContents(), is(contents));
        assertThat(exchange.getImage(), is(image));
        assertThat(exchange.getUser_id(), is(userId));
        assertThat(exchange.getProduct_id(), is(productId));
    }


}
