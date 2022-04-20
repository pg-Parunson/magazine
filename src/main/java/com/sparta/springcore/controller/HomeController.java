package com.sparta.springcore.controller;

import com.sparta.springcore.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://3.38.106.41/")
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpSession httpSession) {
        if (httpSession != null && userDetails != null) {
            httpSession.setAttribute("userNo", userDetails.getUser().getId());
            httpSession.setAttribute("nickname", userDetails.getNickName());
            httpSession.setAttribute("username", userDetails.getUsername());
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("nickname", userDetails.getNickName());
        }
        return "index";
    }
}
