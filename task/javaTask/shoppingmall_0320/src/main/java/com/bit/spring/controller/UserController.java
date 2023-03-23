package com.bit.spring.controller;

import com.bit.spring.model.UserDTO;
import com.bit.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/auth")
    public String auth(HttpSession session, RedirectAttributes redirectAttributes, UserDTO attempt){
        UserDTO result = userService.auth(attempt);

        if(result != null){
            session.setAttribute("logIn", result);

            return "/user/silver";
        }else{
            redirectAttributes.addFlashAttribute("loginMsg", "인증실패! 로그인정보를 확인해주세요.");
            redirectAttributes.addFlashAttribute("state", "loginFail");
            return "redirect:/user/login";
        }
    }
    @PostMapping("register")
    public String register(UserDTO attempt, RedirectAttributes redirectAttributes){
        System.out.println(attempt);
        if(userService.register(attempt)){
            redirectAttributes.addFlashAttribute("state", "registerSuccess");
            return "redirect:/user/login";
        }else{
            redirectAttributes.addFlashAttribute("registerMsg", "중복된 아이디로 가입하실 수 없습니다.");
            redirectAttributes.addFlashAttribute("state", "registerFail");
            return "redirect:/user/login";
        }

    }
    @GetMapping("silver")
    public String showSilver(HttpSession session){
        return "user/silver";
    }

    @RequestMapping("/login")
    public String showLogin(Model model){

        if(model.getAttribute("loginMsg")==null){
            model.addAttribute("loginMsg", "로그인 정보를 입력해주세요.");
        }
        if(model.getAttribute("registerMsg")==null){
            model.addAttribute("registerMsg", "beomMall에 오신것을 환영합니다.");
        }
        return "user/login";
    }
}
