package kr.ac.jejunu.exchange;

import kr.ac.jejunu.exchange.Model.Thumbup;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThumbupTest {
    public static final String PATH ="/api/thumbup";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){
        Integer thumbId = 1;
        String userId ="user2";
        Integer productId=1;

        Thumbup thumbup =restTemplate.getForObject(PATH+"/"+thumbId, Thumbup.class);

        assertThat(thumbup.getThumbupId(), is(thumbId));
        assertThat(thumbup.getUserId(), is(userId));
        assertThat(thumbup.getProductId(), is(productId));
    }

    @Test
    public void list(){
        List<Thumbup> thumbups = restTemplate.getForObject(PATH+"/list", List.class);
        assertThat(thumbups, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){

        String userId ="user2";
        Integer productId=2;
        Thumbup createThumbup = createThumbup(userId, productId);
        validate(userId,productId,createThumbup);
    }

    @Test
    public void update(){
        String userId ="user2";
        Integer productId=2;
        Thumbup createThumbup = createThumbup(userId, productId);
        validate(userId,productId,createThumbup);
        createThumbup.setUserId("user5");
        restTemplate.put(PATH, createThumbup);
        validate("user5", productId, createThumbup);
    }

    @Test
    public void delete(){
        String userId ="user2";
        Integer productId=2;
        Thumbup createThumbup = createThumbup(userId, productId);
        validate(userId,productId,createThumbup);
        restTemplate.delete(PATH+"/"+createThumbup.getThumbupId());

        Thumbup deleteThumbup = restTemplate.getForObject(PATH+"/"+createThumbup.getThumbupId(),Thumbup.class);
        assertThat(deleteThumbup.getUserId(), is(nullValue()));

    }

    private Thumbup createThumbup(String userId, Integer productId) {
        Thumbup thumbup = new Thumbup();
        thumbup.setProductId(productId);
        thumbup.setUserId(userId);
        return restTemplate.postForObject(PATH, thumbup, Thumbup.class);
    }

    private void validate(String userId, Integer productId, Thumbup createThumbup) {
        Thumbup resultThumbup=restTemplate.getForObject(PATH+"/"+createThumbup.getThumbupId(), Thumbup.class);
        assertThat(resultThumbup.getUserId(), is(userId));
        assertThat(resultThumbup.getProductId(), is(productId));
    }
}
