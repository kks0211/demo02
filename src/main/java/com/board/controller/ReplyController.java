package com.board.controller;

import com.board.config.http.BaseResponse;
import com.board.config.http.BaseResponseCode;
import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;
import com.board.exception.BaseException;
import com.board.service.ReplyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

    private static final Logger log = LoggerFactory.getLogger(ReplyController.class);

    private ReplyService replyService;

    @PreAuthorize("isAuthenticated()")
    //@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse<Long> create(@RequestBody ReplyVO vo) {
        log.info("vo : {}", vo);

        //int result = replyService.regist(vo);
        //log.info("result : {} ", result);
        // return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        if (StringUtils.isEmpty(vo.getReply()) || vo.getReply() == "") {
            log.info("vo getReply : {}", vo.getReply());
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"댓글", "Reply"});
        }

        if(StringUtils.isEmpty(vo.getReplyer())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"작성자", "Replyer"});
        }

        int result = replyService.regist(vo);

        //return new BaseResponse<Long>(vo.getRno());

        if (result == 1) {
            throw new BaseException(BaseResponseCode.SUCCESS);
        } else {
            throw new BaseException(BaseResponseCode.ERROR);
        }
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

    @PreAuthorize("principal.username == #vo.replyer")
    @DeleteMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
        return replyService.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("principal.username == #vo.replyer")
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> update(@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {
        log.info("vo : {}", vo);
        log.info("rno : {}", rno);
        vo.setBno(rno);

        return replyService.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
