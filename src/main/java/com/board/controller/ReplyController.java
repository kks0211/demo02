package com.board.controller;

import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;
import com.board.service.ReplyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

    private static final Logger log = LoggerFactory.getLogger(ReplyController.class);

    private ReplyService replyService;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
        log.info("vo : {}", vo);
        int result = replyService.regist(vo);
        log.info("result : {} ", result);
        return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /*@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
        Criteria cri = new Criteria(page, 10);

        return new ResponseEntity<>(replyService.getList(cri, bno), HttpStatus.OK);

    }*/

    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
        Criteria cri = new Criteria(page, 10);

        return new ResponseEntity<>(replyService.getListPage(cri, bno), HttpStatus.OK);

    }

    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
        return new ResponseEntity<>(replyService.read(rno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        return replyService.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> update(@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {
        log.info("vo : {}", vo);
        log.info("rno : {}" + rno);
        vo.setBno(rno);

        return replyService.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
