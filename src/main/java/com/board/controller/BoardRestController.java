package com.board.controller;

import com.board.domain.BoardVO;
import com.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardRestController {
    private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

    @Autowired
    private BoardService service;

    @PostMapping("/board/write")
    public ResponseEntity<String> registerPost(BoardVO vo) throws Exception {

        ResponseEntity<String> entity = null;
        try {
            service.regist(vo);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @PutMapping("/board/update/{bno}")
    public BoardVO updateBoard(@RequestBody BoardVO vo) throws Exception {
        service.modify(vo);
        return vo;

        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/board/delete/{bno}")
    public Long deleteBoard(@PathVariable Long bno) throws Exception {
        service.remove(bno);
        return bno;
    }
}
