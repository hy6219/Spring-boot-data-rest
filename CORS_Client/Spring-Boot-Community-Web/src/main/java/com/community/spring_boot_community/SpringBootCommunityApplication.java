package com.community.spring_boot_community;

import com.community.spring_boot_community.domain.Board;
import com.community.spring_boot_community.domain.User;
import com.community.spring_boot_community.domain.enums.BoardType;
import com.community.spring_boot_community.repository.BoardRepository;
import com.community.spring_boot_community.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommunityApplication.class, args);
    }

    /*
    * CommandLineRunner를 이용하여 어플리케이션 구동 후 특정 코드 실행
    * -테스트데이터를 생성하여 데모프로젝트 실행/테스트
    *
    * 한명의 회원을 생성하여 글 200개 작성
    * */
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               BoardRepository boardRepository) throws Exception{
        //람다 이용->like js arrow func
        return (args)->{
          //user 생성
            User user=User.builder()
                  .name("test")
                  .password("test")
                  .email("test@test.com")
                  .createdDate(LocalDateTime.now())
                  .updatedDate(LocalDateTime.now())
                  .build();
            userRepository.save(user);
          //Board 200개를 생성<-IntStream으로 1부터 200까지 값을 생성해서 Board의 PK값으로 넣어주기
            IntStream.rangeClosed(1,200).forEach(idx->{
                boardRepository.save(
                        Board.builder()
                                .user(user)
                                .title("테스트")
                                .subTitle("서브 타이틀")
                                .content("테스트 게시글")
                                .boardType(BoardType.free)
                                .createdDate(LocalDateTime.now())
                                .updatedDate(LocalDateTime.now())
                                .build()
                );
            });
        };
    }
}
