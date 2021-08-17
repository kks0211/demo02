package com.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(ReplyController.class);

    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {
        log.info("access Denied : {}", auth);
        model.addAttribute("msg", "Access Denied");
    }

    @GetMapping("/customLogin")
    public String loginInput(String error, String logout, Model model) {
        log.info("error : {}", error);
        log.info("logout : {}", logout);

        if (error != null) {
            model.addAttribute("error", "login Error Check your account");
        }
        if (logout != null) {
            model.addAttribute("logout", "logout!!");
        }
        return "/customLogin";
    }

    @GetMapping("/customLogout")
    public String logoutGet(){
        log.info("logout");
        return "/customLogout";
    }

}
