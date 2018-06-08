package kr.ac.jejunu.exchange;


import kr.ac.jejunu.exchange.Model.User;
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
public class UserTest {
    public static final String PATH = "/api/user";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){

        User user = new User();
        user.setUserId("user2");
        user.setName("aaa");
        user.setPassword("aaaa");
        user.setEmail("user@daum.net");
        user.setPhone("010-0000-0000");

        validate( user);
    }

    @Test
    public void list(){
        List<User> users = restTemplate.getForObject(PATH + "/list", List.class);
        assertThat(users, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        String userId  = "user2";
        User createUser = createUser(userId);
        validate(createUser);
    }

    @Test
    public void update(){
        String userId ="user5";
        User createUser = createUser(userId);
        createUser.setName("bbbbb");
        restTemplate.put(PATH, createUser);
        validate(createUser);
    }

    @Test
    public void delete(){
        String userId = "user6";
        User createUser = createUser(userId);
        validate(createUser);
        restTemplate.delete(PATH+"/"+createUser.getUserId());
        User deleteUser = restTemplate.getForObject(PATH+"/"+createUser.getUserId(), User.class);
        assertThat(deleteUser.getUserId(), is(nullValue()));
        assertThat(deleteUser.getName(), is(nullValue()));


    }

    private void validate( User createdUser) {
        User resultUser = restTemplate.getForObject(PATH + "/" + createdUser.getUserId(), User.class);
        assertThat(resultUser.getName(), is(createdUser.getName()));
        assertThat(resultUser.getPassword(), is(createdUser.getPassword()));
        assertThat(resultUser.getEmail(), is(createdUser.getEmail()));
        assertThat(resultUser.getPhone(), is(createdUser.getPhone()));
    }

    private User createUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setName("aaa");
        user.setPassword("aaaa");
        user.setEmail("user@daum.net");
        user.setPhone("010-0000-0000");
        return restTemplate.postForObject(PATH, user, User.class);
    }
}
