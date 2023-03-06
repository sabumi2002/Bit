package servlet.reply;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.MovieController;
import controller.ReviewController;
import controller.UserController;
import model.MovieDTO;
import model.ReviewDTO;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "PrintUtilServlet", value = "/PrintUtilServlet")
public class PrintUtilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();

        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        int id = Integer.parseInt(request.getParameter("id"));
        session.setAttribute("currentPage", "/movie/movie-detail.jsp?id=" + id);

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        MovieController movieController = new MovieController(connectionMaker);
        UserController userController = new UserController(connectionMaker);
        ReviewController reviewController = new ReviewController(connectionMaker);

        ArrayList<ReviewDTO> reviewList = reviewController.selectAll(id);

        MovieDTO m = movieController.selectOne(id);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
