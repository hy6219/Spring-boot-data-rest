package com.example.bootrest.service;

import com.example.bootrest.domain.Board;
import com.example.bootrest.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    
    public Page<Board> findBoardList(Pageable pageable){
        //실제 페이지와 pageable의 페이지는 1차이가 남
        return boardRepository.findAll(PageRequest.of(
                pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize()
        ));
    }

    public Board findBoardByIdx(Long idx){
        return boardRepository.findById(idx).orElseThrow(RuntimeException::new);
    }
}
