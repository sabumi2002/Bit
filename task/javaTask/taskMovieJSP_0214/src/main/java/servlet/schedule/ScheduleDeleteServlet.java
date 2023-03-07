package servlet.schedule;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.CinemaController;
import controller.ScheduleController;
import model.CinemaDTO;
import model.ScheduleDTO;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ScheduleDeleteServlet", value = "/schedule/ScheduleDeleteServlet")
public class ScheduleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject object = new JsonObject();


        try {
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");
            int schduleId = Integer.parseInt(request.getParameter("id"));

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            ScheduleController scheduleController = new ScheduleController(connectionMaker);

            ScheduleDTO scheduleDTO = scheduleController.selectOne(schduleId);

            if (scheduleDTO==null || logIn.getRank()!=1){
                throw new NullPointerException();
            }

            scheduleController.delete(schduleId);

            object.addProperty("status", "success");

        } catch (Exception e){
            object.addProperty("status", "fail");
        }

        writer.print(object);
    }
}
