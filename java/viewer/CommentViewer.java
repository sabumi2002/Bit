package viewer;

import controller.CommentController;
import model.BoardDTO;
import model.CommentDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentViewer {
    private Scanner SCANNER;
    private BoardViewer boardViewer;
    private UserDTO login;
    private CommentController commentController;
    private ArrayList<CommentDTO> list;
    private int boardId;

    public CommentViewer(Scanner scanner){
        commentController = new CommentController();
        SCANNER = scanner;
        list = new ArrayList<>();
    }
    public void setBoardViewer(BoardViewer boardViewer){
        this.boardViewer = boardViewer;
    }
    public void setLogin(UserDTO login){
        this.login = login;
    }

    public void printCommentList(int boardId){  // 댓글 list 보여주기
        this.boardId = boardId;

        list = commentController.selectAllBoardId(boardId);

        if(list.isEmpty()){
            System.out.println("---------댓글리스트---------------");
            System.out.println("등록된 댓글이 없습니다.");
        }else{
            System.out.println("---------댓글리스트---------------");
            for(CommentDTO c : list){
                System.out.printf("%d. <%s> %s\n", c.getId(), c.getWriterNickname(), c.getContent()); // 댓글번호. <작성자닉네임> 내용
            }
        }
        String message = "1. 댓글 작성 2. 수정 3. 삭제 4. 돌아가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if(userChoice == 1){
            insertCommentList();
        }else if(userChoice == 2){
            update();
        }else if(userChoice == 3){
            delete();
        }else if(userChoice == 4){

        }
    }
    public void insertCommentList(){ // 댓글 추가

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setWriterId(login.getId());
        commentDTO.setWriterNickname(login.getNickname());
        commentDTO.setBoardId(boardId);
        String message = "글의 내용을 입력해주세요.";
        commentDTO.setContent(ScannerUtil.nextLine(SCANNER, message));

        commentController.add(commentDTO);
        printCommentList(boardId);
    }
    public void update(){ // 댓글 수정

        if(list.isEmpty()){
            System.out.println("수정할 댓글이 없습니다.");
            printCommentList(boardId);
        } else{
            String message = "수정하실 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);


            if (!auth()){
                System.out.println("권한이 업습니다.");
                printCommentList(boardId);
            }else{
                while (userChoice != 0 && !list.contains(new CommentDTO(userChoice))) {
                    System.out.println("잘못 입력하셨습니다.");
                    userChoice = ScannerUtil.nextInt(SCANNER, message);
                }

                if (userChoice == 0) {  // 뒤로가기
                    printCommentList(boardId);
                }
                CommentDTO c = commentController.selectById(userChoice);
                message = "새로운 내용을 입력해주세요.";
                c.setContent(ScannerUtil.nextLine(SCANNER, message));

                commentController.update(c);
                printCommentList(boardId);
            }


        }

    }
    public boolean auth(){
        for(CommentDTO c: list){
            if (c.getWriterId() == login.getId()){
                return true;
            }
        }
        return false;
    }
    public void delete(){ // 댓글 삭제
        if(list.isEmpty()){
            System.out.println("삭제할 댓글이 없습니다.");
            printCommentList(boardId);
        }else{
            String message = "삭제하실 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (!auth()){
                System.out.println("권한이 업습니다.");
                printCommentList(boardId);
            }else{
                while (userChoice != 0 && !list.contains(new CommentDTO(userChoice))) {
                    System.out.println("잘못 입력하셨습니다.");
                    userChoice = ScannerUtil.nextInt(SCANNER, message);
                }

                if (userChoice == 0) {  // 뒤로가기
                    printCommentList(boardId);
                }else{
                    message = "정말로 삭제하시겠습니까? Y/N";
                    String yesNo = ScannerUtil.nextLine(SCANNER, message);
                    if (yesNo.equalsIgnoreCase("Y")) {
                        commentController.delete(userChoice);
                        printCommentList(boardId);
                    } else {
                        printCommentList(boardId);
                    }
                }
            }
        }
    }
    public void userUpdate(UserDTO u){  // 댓글 실시간 업데이트
        ArrayList<CommentDTO> list = commentController.selectAllWriteId(u.getId());
        for(CommentDTO c : list){
            c.setWriterNickname(u.getNickname());
            commentController.update(c);
        }
    }
    public void userDelete(UserDTO u){  // 댓글 실시간 업데이트 삭제
        ArrayList<CommentDTO> list = commentController.selectAllWriteId(u.getId());
        for(CommentDTO c : list){
            commentController.delete(c.getId());
        }
    }

}


















