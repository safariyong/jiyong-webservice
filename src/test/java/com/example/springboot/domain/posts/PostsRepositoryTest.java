package com.example.springboot.domain.posts;

import com.example.springboot.Domain.posts.Posts;
import com.example.springboot.Domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동실행.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After //Junit 단위테스트 끝날때마다 수행.
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update 쿼리 실행. 있으면 update, 없으면 insert.
        postsRepository.save(Posts.builder().title(title).content(content).author("pjayk12@gmail.com").build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블 posts에 모든 데이터 조회.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
