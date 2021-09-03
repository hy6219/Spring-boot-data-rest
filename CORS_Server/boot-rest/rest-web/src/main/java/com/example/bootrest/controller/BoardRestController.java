package com.example.bootrest.controller;

import com.example.bootrest.domain.Board;
import com.example.bootrest.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable){
        Page<Board> boards=boardRepository.findAll(pageable);
        PagedModel.PageMetadata pageMetadata=new PagedModel.PageMetadata(pageable.getPageSize(),
                boards.getNumber(),boards.getTotalElements());
        PagedModel<Board> resources=new PagedModel<>(boards.getContent(),pageMetadata);
        resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(resources);
        /*
         * PageResources->PagedModel
         * ⓐ @PageableDefault : 한 페이지에 보여질 게시글 갯수, 정렬 기준을 세워주기 위한 어노테이션
         *
         * */
    }

}
