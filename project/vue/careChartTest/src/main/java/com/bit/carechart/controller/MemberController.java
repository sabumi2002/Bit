package com.bit.carechart.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MemberController {
    @GetMapping("/member")
    public String showMember(){
        return "member/meberInfo";
    }
}
