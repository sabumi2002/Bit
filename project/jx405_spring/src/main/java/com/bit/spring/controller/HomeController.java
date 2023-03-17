package com.bit.spring.controller;

import com.bit.spring.model.UserDTO;
import com.bit.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    UserService userService;

    @Autowired
    public HomeController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        //userService.encrypt();

        return "index";
    }
//
//    @RequestMapping("/{id}")
//    public String showId(Model model, @PathVariable int id){
//        model.addAttribute("id", memberService.selectOne(id));
//
//        return "index";
//    }
//
//    // 첫번쨰 방법: url만 적어줄경우 자동으로 get이 설정된다.
//    //      @RequestMapping("/show")
//    // 두번째 방법: value에 url입력, method에 통신방식 입력
//    //      @RequestMapping(value= "/show", method= RequestMethod.GET)
//    // 세번째 방법: get어노테이션 사용
//    //      @GetMapping("/show")
//    @GetMapping("/show")
//    public String showInfo(Model model, UserDTO memberDTO, int id, String name, String rank){
//        System.out.println("id: "+ memberDTO.getId());
//        System.out.println("name: "+memberDTO.getName());
//        System.out.println("rank: "+ rank);
//
//        model.addAttribute("id", id);
//        model.addAttribute("name", name);
//        model.addAttribute("rank", rank);
//
//        return "show";
//    }
}
