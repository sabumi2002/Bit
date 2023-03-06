package servlet.schedule;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.CinemaController;
import controller.MovieController;
import controller.ScheduleController;
import model.CinemaDTO;
import model.MovieDTO;
import model.ScheduleDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@WebServlet(name = "ScheduleServlet", value = "/schedule/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject result = new JsonObject();

        try{
            int id = Integer.parseInt(request.getParameter("id"));

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            ScheduleController scheduleController = new ScheduleController(connectionMaker);
            MovieController movieController = new MovieController(connectionMaker);
            CinemaController cinemaController = new CinemaController(connectionMaker);

            ScheduleDTO scheduleDTO = scheduleController.selectOne(id);
            MovieDTO movieDTO = movieController.selectOne(scheduleDTO.getMovieId());
            CinemaDTO cinemaDTO = cinemaController.selectOne(scheduleDTO.getCinemaId());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 (EEE)");

//            상영시간 start , end
            String startTime = scheduleDTO.getRunningTime();
            String endTime = scheduleController.minuteToHour(startTime, Integer.parseInt(movieDTO.getLength()));

            result.addProperty("id", id);
            result.addProperty("startRunningTime", startTime);
            result.addProperty("endRunningTime", endTime);
            result.addProperty("screeningDate", sdf.format(scheduleDTO.getScreeningDate()));
            result.addProperty("movieTitle", movieDTO.getTitle());
            result.addProperty("movieRank", movieDTO.getRank());
            result.addProperty("movieImg", movieDTO.getImg());
            result.addProperty("movieLength", movieDTO.getLength());
            result.addProperty("cinemaName", cinemaDTO.getName());
            result.addProperty("cinemaLocation", cinemaDTO.getLocation());
            result.addProperty("cinemaPhone", cinemaDTO.getPhoneNumber());

            result.addProperty("status", "success");

        }catch(Exception e){
            result.addProperty("status", "fail");
        }

        writer.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}



















