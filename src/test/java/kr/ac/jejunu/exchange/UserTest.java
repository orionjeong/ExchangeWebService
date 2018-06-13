package kr.ac.jejunu.exchange;


import kr.ac.jejunu.exchange.Model.User;
import kr.ac.jejunu.exchange.Util.StateCode;
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
        String userId = "user2";
        String name = "aaa";
        String password = "aaaa";
        String email = "user@daum.net";
        String phone = "010-0000-0000";
        User user = restTemplate.getForObject(PATH + "/" + userId, User.class);
        validate(userId,name ,password ,email ,phone, user);
    }

    @Test
    public void list(){
        List<User> users = restTemplate.getForObject(PATH + "/list", List.class);
        assertThat(users, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass1");
        user.setName("user1");
        user.setEmail("aaa@daum.net");
        user.setPhone("010-0000-0000");
        StateCode stateCode = restTemplate.postForObject(PATH, user, StateCode.class);

        assertThat(stateCode.getStateCode(), is("200"));



    }

    @Test
    public void update(){
        String userId ="user5";
        String name = "aaa";
        String password = "aaaa";
        String email = "user@daum.net";
        String phone = "010-0000-0000";
        User createUser = createUser(userId, name, password, email, phone);
        createUser.setName("bbbbb");
        restTemplate.put(PATH, createUser);
        validate(userId, "bbbbb", password, email, phone, createUser);
    }

    @Test
    public void delete(){
        String userId = "user4";
        String name = "aaa";
        String password = "aaaa";
        String email = "user@daum.net";
        String phone = "010-0000-0000";
        User createUser = createUser(userId, name, password, email, phone);
        validate(userId, name, password, email, phone, createUser);
        restTemplate.delete(PATH+"/"+ createUser.getUserId());
        User deleteUser = restTemplate.getForObject(PATH+"/"+ createUser.getUserId(), User.class);
        assertThat(deleteUser.getUserId(), is(nullValue()));
        assertThat(deleteUser.getName(), is(nullValue()));


    }

    private void validate(String userId, String name, String password, String email, String phone, User createdUser) {
        User resultUser = restTemplate.getForObject(PATH + "/" + createdUser.getUserId(), User.class);
        assertThat(resultUser.getUserId(), is(userId));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
        assertThat(resultUser.getEmail(), is(email));
        assertThat(resultUser.getPhone(), is(phone));
    }

    private User createUser(String username, String name, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        return restTemplate.postForObject(PATH, user, User.class);
    }
}
