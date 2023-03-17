package com.bit.spring.controller;

import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import com.bit.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("showAll/{pageNo}")
    public String showAll(Authentication authentication, Model model, @PathVariable int pageNo) {
//        if (session.getAttribute("logIn") == null) {
//            // redirectAttributes.addFlashAttribute는 우리가 눈에 보이지않게 왔다갔다 한다.
//            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
//            // model.addAttribute는 쿼리로 왔다갔다함.
////            model.addAttribute("message", "다시 로그인해주세요.");
//            return "redirect:/";
//        }
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());

        model.addAttribute("list", boardService.selectAll(pageNo));
        model.addAttribute("paging", setPages(pageNo, boardService.selectLastPage()));
        model.addAttribute("current", pageNo);
        model.addAttribute("pagingAddr", "/board/showAll");

        return "/board/showAll";
    }

    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int id) {
        if (session.getAttribute("logIn") == null) {
            redirectAttributes.addFlashAttribute("message", "다시 로그인 해주세요.");
            return "redirect:/";
        }

        BoardDTO b = boardService.selectOne(id);
        if (b == null) {
            redirectAttributes.addFlashAttribute("message", "존재하지 않는 글 번호입니다.");
            return "redirect:/board/showAll/1";
        }
        model.addAttribute("result", b);
        int logInId = ((UserDTO) session.getAttribute("logIn")).getId();
        model.addAttribute("logInId", logInId);
        return "/board/showOne";    // showOne.jsp 실행시켜줘~
    }

    @PostMapping("upsert")
    public String upsert(HttpSession session, String attemptId, BoardDTO boardDTO){
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if(attemptId.matches("^\\d+$")){
            boardDTO.setId(Integer.parseInt(attemptId));
            BoardDTO origin = boardService.selectOne(boardDTO.getId());
            origin.setTitle(boardDTO.getTitle());
            origin.setContent(boardDTO.getContent());
            boardService.update(origin);
        }else{
            boardDTO.setWriterId(logIn.getId());
            boardService.insert(boardDTO);
        }


        return "redirect:/board/showOne/"+ boardDTO.getId();
    }

    @GetMapping("search/{pageNo}")
    public String search(@PathVariable int pageNo, String keyword, Model model) {
//        model.addAttribute("list", boardService.selectByKeyword(keyword));
        Map<String, Object> map = boardService.selectByKeyword(keyword, pageNo);


        model.addAttribute("list", map.get("list"));


        model.addAttribute("pagingAddr", "/board/search/");
        model.addAttribute("keyword", keyword);
        model.addAttribute("paging", setPages(pageNo, boardService.countSearchResult(keyword)));
        return "/board/showAll";
    }

    private HashMap<String, Integer> setPages(int pageNo, int totalPage) {
        HashMap<String, Integer> paging = new HashMap<>();
        int start = 0;
        int end = 0;

        if (totalPage < 5) {
            start = 1;
            end = totalPage;
        } else if (pageNo < 3) {
            start = 1;
            end = 5;
        } else if (pageNo > totalPage - 3) {
            start = totalPage - 4;
            end = totalPage;
        } else {
            start = pageNo - 2;
            end = pageNo + 2;
        }
        paging.put("start", start);
        paging.put("end", end);
        paging.put("totalPage", totalPage);
        paging.put("current", pageNo);


        return paging;
    }

    @GetMapping("upsert")
    public String showUpsert(@PathVariable @Nullable int id) {
        System.out.println(id);

        return "/board/upsert";
    }

    //
    @GetMapping("update/{id}")
    public String showUpdate(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int id) {
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
            return "redirect:/";
        }

        BoardDTO b = boardService.selectOne(id);
        if (b == null || b.getWriterId() != logIn.getId()) {
            redirectAttributes.addFlashAttribute("message", "유효하지 않는 접근입니다.");
            return "redirect:/board/showAll/1";
        }

        model.addAttribute("board", b);
        return "/board/upsert";
    }
//
//    @PostMapping("update")
//    public String updateBoard(HttpSession session, BoardDTO boardDTO){
//        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            return "redirect:/";
//        }
//
//        BoardDTO origin = boardService.selectOne(boardDTO.getId());
//        if(origin == null){
//            return "redirect:/board/showAll/1";
//        }
//        origin.setTitle(boardDTO.getTitle());
//        origin.setContent(boardDTO.getContent());
//
//        boardService.update(origin);
//
//        return "redirect:/board/showOne/"+boardDTO.getId();
//    }
//
//    @GetMapping("delete/{id}")
//    public String delete(HttpSession session, RedirectAttributes redirectAttributes, @PathVariable int id){
//        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요.");
//            return "redirect:/";
//        }
//
//        BoardDTO b = boardService.selectOne(id);
//        if(b == null|| b.getWriterId() != logIn.getId()){
//            redirectAttributes.addFlashAttribute("message", "유효하지 않는 접근입니다.");
//            return "redirect:/board/showAll/1";
//        }
//
//        boardService.delete(id);
//        return "redirect:/board/showAll/1";
//    }

    @GetMapping("write")
    public String showWrite() {

        return "/board/upsert";
    }

    @PostMapping("write")
    public String write(HttpSession session, BoardDTO boardDTO) {
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        System.out.println("insert전 boardDTO: " + boardDTO);
        boardDTO.setWriterId(logIn.getId());
        boardService.insert(boardDTO);
        System.out.println("insert후 boardDTO: " + boardDTO);

        return "redirect:/board/showOne/" + boardDTO.getId();
    }

}



























