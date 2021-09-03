package com.community.spring_boot_community.controller;

import com.community.spring_boot_community.domain.Board;
import com.community.spring_boot_community.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardService boardService;
    private final String root="board";
    //중괄호를 이용해서 매핑 경로를 여러개로 받음
    @GetMapping({"","/"})
    public String board(@RequestParam(defaultValue = "0") Long idx, Model model){
        Board board=boardService.findBoardByIdx(idx);
        log.info("selected:{}",board);
        model.addAttribute("board",board);
        return root+"/form";
    }

    @GetMapping("/list")
    public String getList(@PageableDefault Pageable pageable, Model model){
        Page<Board> list=boardService.findBoardList(pageable);
        log.info("boardList: "+list);
        model.addAttribute("boardList",list);
        return root+"/list";
    }
}
