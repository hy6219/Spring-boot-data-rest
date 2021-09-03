package com.community.spring_boot_community;

import com.community.spring_boot_community.domain.Board;
import com.community.spring_boot_community.domain.User;
import com.community.spring_boot_community.domain.enums.BoardType;
import com.community.spring_boot_community.repository.BoardRepository;
import com.community.spring_boot_community.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    private final String email="test@test.com";
    private final String boardTitle="테스트";

    @BeforeEach
    public void init(){
        User user= userRepository.save(User.builder()
                .name("test")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build());
        System.out.println(user);
        Board board=boardRepository.save(
                Board.builder()
                        .title(boardTitle)
                        .subTitle("서브 타이틀")
                        .content("테스트 게시글")
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedTime(LocalDateTime.now())
                        .user(user)
                        .build()
        );
        System.out.println(board);
    }

    @Test
    public void createdTest(){
        //도메인이 잘 생성되었는지 테스트
        User user=userRepository.findByEmail(email);
        assertThat(user.getName(),is("test"));
        assertThat(user.getPassword(),is("test"));
        assertThat(user.getEmail(),is(email));

        Board board=boardRepository.findByUser(user);
        assertThat(board.getTitle(),is(boardTitle));
        assertThat(board.getSubTitle(),is("서브 타이틀"));
        assertThat(board.getContent(),is("테스트 게시글"));
        assertThat(board.getBoardType(),is(BoardType.free));
    }

}
