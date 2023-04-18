package com.bit.carechart.controller;

import com.bit.carechart.model.MemberDTO;
import com.bit.carechart.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private MemberService userService;

    public HomeController(MemberService userService) {
        this.userService = userService;
    }

//    @RequestMapping("/")
    public String showIndex(HttpSession session, Model model) {
        System.out.println("여긴 테스트 구역");

        List<MemberDTO> userList = userService.selectAll();

        for(MemberDTO u : userList) {
            System.out.println(u);
        }

        return "index";


//        return "redirect:/mypage/showBasket";
    }
}
