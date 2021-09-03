package com.community.spring_boot_community.service;

import com.community.spring_boot_community.domain.Board;
import com.community.spring_boot_community.repository.BoardRepository;
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
        return boardRepository.findById(idx).orElse(new Board());
    }
}
