package com.board.controller;

import com.board.domain.SampleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/sample/*")
public class SampleController {

    private static final Logger log = LoggerFactory.getLogger(SampleController.class);
    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
    // false));
    // }

    @RequestMapping(value = "/getText", produces = "text/plain;charset=utf-8")
    public String sample() {
        log.info("/sample {}" + MediaType.TEXT_PLAIN_VALUE);
        return "안녕";
    }

    @GetMapping(value = "/getSample")
    public SampleVO getSample() {
        SampleVO vo = SampleVO.builder().mno(200).firstName("스타").lastName("로드").build();
        log.info("sampel : {}", vo);
        return vo;
    }

    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {
        SampleVO vo = SampleVO.builder().mno(0).firstName("" + height).lastName("" + weight).build();

        ResponseEntity<SampleVO> result = null;

        if(height < 150){
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        log.info("result : {}", result);

        return result;

    }

    @PostMapping("/ticket")
    public SampleVO convert(@RequestBody SampleVO vo){
        log.info("vo :  {} ", vo);
        return vo;
    }


    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {

        files.forEach(file -> {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());

        });
    }

}
