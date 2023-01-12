package viewer;

import controller.BoardController;
import model.BoardDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardViewer {
    private BoardController boardController;
    private UserViewer userViewer;
    private CommentView commentView;
    private final Scanner SCANNER;
    private UserDTO login;

    public BoardViewer(Scanner scanner) {
        boardController = new BoardController();
        SCANNER = scanner;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }
    public void setCommentView(CommentView commentView){
        this.commentView = commentView;
    }

    public void setLogin(UserDTO login) {
        this.login = login;
    }


    public void showMenu() {
        String message = "1. 새 글 작성하기 2. 글 목록 보기 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeBoard();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void writeBoard() {
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setWriterId(login.getId());
        boardDTO.setWriterNickname(login.getNickname());

        String message = "글의 제목을 입력해주세요.";
        boardDTO.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "글의 내용을 입력해주세요.";
        boardDTO.setContent(ScannerUtil.nextLine(SCANNER, message));

        boardController.add(boardDTO);
    }

    private void printList() {
        ArrayList<BoardDTO> list = boardController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 글이 없습니다.");
        } else {
            for (BoardDTO b : list) {
                System.out.printf("%d. %s\n", b.getId(), b.getTitle());
            }

            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new BoardDTO(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        BoardDTO boardDTO = boardController.selectOne(id);
        System.out.println("=================================================");
        System.out.println(boardDTO.getTitle());
        System.out.println("-------------------------------------------------");
        System.out.println("글 번호: " + boardDTO.getId());
        System.out.println("글 작성자: " + boardDTO.getWriterNickname());
        System.out.println("-------------------------------------------------");
        System.out.println(boardDTO.getContent());
        System.out.println("=================================================");

        String message;
        int userChoice;

        if (boardDTO.getWriterId() == login.getId()) {
            message = "1. 수정 2. 삭제 3. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        } else {
            message = "3. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 3);
        }

        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }

    private void update(int id) {
        BoardDTO b = boardController.selectOne(id);

        String message = "새로운 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        boardController.update(b);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            boardController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
















    /*
    private final Scanner SCANNER;
    private BoardContoller boardContoller;

    BoardViewer(){
        SCANNER = new Scanner(System.in);
        boardContoller = new BoardContoller();
    }
    public void showMenu(){
        while(true){
            String message = "1. 입력 2. 목록 보기 3.종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1){
                writeBoard();
            }else if(userChoice == 2){
                printList();
            }else if(userChoice == 3){
                System.out.println("사용해주셔서 감사합니다");
                break;
            }
        }
    }
    private void writeBoard(){
        BoardDTO b = new BoardDTO();
        String message;

        message = "글의 작성자를 입력해주세요.";
        b.setWriter(ScannerUtil.nextLine(SCANNER, message));
        message = "글의 제목을 입력해주세요.";
        b.setTitle((ScannerUtil.nextLine(SCANNER, message)));
        message = "글의 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        // array = ArrayUtil.add(array, b);
        boardContoller.insertBoard(b);
    }
    private void printList(){
        if(boardContoller.empty()){ // 비어있는지 확인 ArrayUtil.isEmpty(array)
            System.out.println("아직 등록된 글이 존재하지 않습니다.");
        }else{
//            for(Board b:array){
//                System.out.printf("%d. %s\n", b.getId(), b.getTitle());
//            }
            boardContoller.printList();
            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            Board b = new Board();
            b.setId(userChoice);

            while(userChoice != 0 && !boardContoller.equals(b)){ // ArrayUtil.contains(array, b)
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                b.setId(userChoice);
            }
            if(userChoice != 0){
                printOne(userChoice);
            }
        }
    }
    private void printOne(int id){
        Board temp = new Board();
        temp.setId(id);

        Board b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));

        b.printBoard();

        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);

        if(userChoice == 1){
            update(id);
        }else if (userChoice == 2){
            delete(id);
        }else{
            printList();
        }
    }
    private void update(int id){
        Board temp = new Board();
        temp.setId(id);

        Board b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));

        String message = "새로운 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        printOne(id);
    }

    private void delete(int id){
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if(yesNo.equalsIgnoreCase("Y")){    // 대소문자 상관없이 스트링 글자 비교할떄 사용하는 메서드
            Board b = new Board();
            b.setId(id);
            array = ArrayUtil.remove(array, b);
            printList();
        }else{
            printOne(id);
        }


    }
*/
}
