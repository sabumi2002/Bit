package day0112;

import viewer.BoardViewer;
import viewer.CommentView;
import viewer.UserViewer;

import java.util.Scanner;

public class Ex03Board02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserViewer userViewer = new UserViewer(scanner);
        BoardViewer boardViewer = new BoardViewer(scanner);
        CommentView commentView = new CommentView(scanner);

        userViewer.setBoardViewer(boardViewer);
        boardViewer.setUserViewer(userViewer);
        commentView.setBoardViewer(boardViewer);

        userViewer.showIndex();
    }
}
