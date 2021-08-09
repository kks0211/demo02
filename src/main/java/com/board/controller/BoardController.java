package com.board.controller;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.Search;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService service;

    /*@GetMapping("")
    public ModelAndView openBoardList(Criteria cri) throws Exception {
        log.info("==========board List========");
        ModelAndView mv = new ModelAndView("/board/restBoardList");
        log.info("list");
        List<BoardVO> list = service.getList(cri);
        mv.addObject("list", list);
        return mv;
    }*/

    @GetMapping("/write")
    public String registerGet() throws Exception {
        return "/tboard/restBoardWrite";
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
    public String list(Criteria cri, Model model) {
        log.info("list : " + cri);
        int total = service.getTotalCount(cri);
        log.info("list total : " + total);

        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
        return "/board/list";
    }

    @GetMapping("/register")
    public String register() {
        return "/board/register";
    }

    @PostMapping("/register")
    public String register(BoardVO vo, RedirectAttributes rttr) {
        log.info("register : " + vo);
        service.regist(vo);

        rttr.addFlashAttribute("result", vo.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get"})
    public String get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
        log.info("/get or /modiry");
        model.addAttribute("board", service.read(bno));
        return "/board/get";
    }

    @GetMapping({"/modify"})
    public String modify(@RequestParam("bno") Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
        log.info("/get or /modiry");
        model.addAttribute("board", service.read(bno));
        return "/board/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardVO vo, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
        log.info("modify : " + vo);

        if (service.modify(vo)) {
            rttr.addFlashAttribute("result", "success");
        }

        /*rttr.addFlashAttribute("pageNum", cri.getPageNum());
        rttr.addFlashAttribute("amount", cri.getAmount());
        rttr.addFlashAttribute("type", cri.getType());
        rttr.addFlashAttribute("keyword", cri.getKeyword());*/

        return "redirect:/board/list" + cri.getListLink();

    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
        log.info("remove ---- " + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        /*rttr.addFlashAttribute("pageNum", cri.getPageNum());
        rttr.addFlashAttribute("amount", cri.getAmount());
        rttr.addFlashAttribute("type", cri.getType());
        rttr.addFlashAttribute("keyword", cri.getKeyword());*/

        return "redirect:/board/list" + cri.getListLink();
    }


}
