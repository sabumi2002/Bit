package servlet.reply;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.ReviewController;
import model.ReviewDTO;
import model.UserDTO;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReplyWriteServlet", value = "/reply/ReplyWriteServlet")
public class ReplyWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();

        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        ReviewController reviewController = new ReviewController(connectionMaker);


        try {
            if (logIn == null) {
                throw new NullPointerException();
            }
            if (!reviewController.isWrite(logIn.getId())) {
                System.out.println("isWrite");
                throw new NullPointerException();
            }
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            String content = request.getParameter("content");
            int rating = Integer.parseInt(request.getParameter("score"));


            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setMovieId(movieId);
            reviewDTO.setWriterId(logIn.getId());
            reviewDTO.setReview(content);
            reviewDTO.setRating(rating);


            reviewController.insert(reviewDTO);

            object.addProperty("status", "success");
        } catch (NullPointerException e) {
            System.out.println("NPE");
            object.addProperty("status", "fail");
        } catch (Exception e) {
            System.out.println("E");
            object.addProperty("status", "fail");
        }
//        response.sendRedirect("/movie/movie-detail.jsp?id=" + movieId);

        writer.print(object);


    }
}
