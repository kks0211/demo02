package com.board.controller;

import com.board.domain.BoardVO;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService service;

    //@GetMapping("")
    public ModelAndView openBoardList() throws Exception {
        log.info("==========board List========");
        ModelAndView mv = new ModelAndView("/board/restBoardList");
        List<BoardVO> list = service.getList();
        mv.addObject("list", list);
        return mv;
    }

    @GetMapping("/write")
    public String registerGet() throws Exception {
        return "/board/restBoardWrite";
    }

    /*@PostMapping("/write")
    @ResponseBody
    public ResponseEntity<String> registerPost(BoardVO vo) throws Exception {
        service.regist(vo);
        //return "redirect:/board";
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*@GetMapping("/{bno}")
    public ModelAndView openBoardDetail(@PathVariable("bno") Long bno) throws Exception {
        ModelAndView mv = new ModelAndView("/board/restBoardDetail");

        BoardVO vo = service.read(bno);
        mv.addObject("board", vo);

        return mv;
    }*/

    /*@ResponseBody
    @PutMapping("/update/{bno}")
    public void updateBoard(@RequestBody BoardVO vo) throws Exception {
        logger.info(">>>>>>>>>>>" + vo);
        service.modify(vo);

        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bno}")
    public ResponseEntity<String> deleteBoard(@PathVariable int bno) throws Exception {
        service.remove(bno);

        return new ResponseEntity<String>(HttpStatus.OK);
    }*/

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(BoardVO vo, RedirectAttributes rttr) {
        log.info("register : " + vo);
        service.regist(vo);

        rttr.addFlashAttribute("result", vo.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, Model model) {
        log.info("/get or /modiry");
        model.addAttribute("board", service.read(bno));
    }


    @PostMapping("/modify")
    public String modify(BoardVO vo, RedirectAttributes rttr) {
        log.info("modify : " + vo);

        if (service.modify(vo)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";

    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        log.info("remove ---- " + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }


}
