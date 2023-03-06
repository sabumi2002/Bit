package servlet.main;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.UserController;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HeaderNavServlet", value = "/HeaderNavServlet")
public class HeaderNavServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        UserController userController = new UserController(connectionMaker);
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        if (logIn == null) {
            object.addProperty("result", "fail");
        } else {


            object.addProperty("logInNickname", userController.selectOne(logIn.getId()).getNickname());

            object.addProperty("result", "success");

        }
        writer.print(object);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
