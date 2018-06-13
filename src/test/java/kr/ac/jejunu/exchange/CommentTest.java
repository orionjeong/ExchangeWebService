package kr.ac.jejunu.exchange;

import kr.ac.jejunu.exchange.Model.Comment;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentTest {
    public static final String PATH="/api/comment";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void get(){
        Integer commentId = 1;
        String userId = "user2";
        Integer productId = 1;
        String contents = "첫 댓글입니다";

        Comment comment = restTemplate.getForObject(PATH+"/"+commentId,Comment.class);

        assertThat(comment.getCommentId(), is(commentId));
        assertThat(comment.getUserId(), is(userId));
        assertThat(comment.getProductId(), is(productId));
        assertThat(comment.getContents(), is(comment));
    }

    @Test
    public void list(){
        List<Comment> comments= restTemplate.getForObject(PATH+"/list", List.class);
        assertThat(comments, not(IsEmptyCollection.empty()));
    }
    @Test
    public void listByproductId(){
        List<Comment> exchanges = restTemplate.getForObject(PATH+"/list/search?productId=1", List.class);
        assertThat(exchanges, not(IsEmptyCollection.empty()));
    }

    @Test
    public void create(){
        String userId = "user2";
        Integer productId = 1;
        String contents = "댓글입니다";
        Comment createComment = createComment(userId, productId, contents);
        validate(userId, contents, productId, createComment);
    }

    @Test
    public void update(){
        String userId = "user2";
        Integer productId = 1;
        String contents = "댓글입니다";
        Comment createComment = createComment(userId, productId, contents);
        validate(userId, contents, productId, createComment);
        createComment.setContents("변경된댓글");
        restTemplate.put(PATH, createComment);
        validate(userId, "변경된댓글", productId, createComment);
    }

    @Test
    public void delete(){
        String userId = "user2";
        Integer productId = 1;
        String contents = "댓글입니다";
        Comment createComment = createComment(userId, productId, contents);
        validate(userId, contents, productId, createComment);
        restTemplate.delete(PATH+"/"+ createComment.getCommentId());
        Comment resultComment = restTemplate.getForObject(PATH+"/"+createComment.getCommentId(), Comment.class);
        assertThat(resultComment.getContents(), is(nullValue()));
    }

    private Comment createComment(String userId, Integer productId, String contents) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setProductId(productId);
        comment.setContents(contents);
        return restTemplate.postForObject(PATH,comment, Comment.class);
    }

    private void validate(String userId, String contents, Integer productId, Comment createComment) {
        Comment resultComment = restTemplate.getForObject(PATH+"/"+createComment.getCommentId(), Comment.class);
        assertThat(resultComment.getContents(), is(contents));
        assertThat(resultComment.getUserId(), is(userId));
        assertThat(resultComment.getProductId(), is(productId));
    }


}
