package servlet.schedule;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.MovieController;
import controller.ScheduleController;
import model.MovieDTO;
import model.ScheduleDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ScheduleInsertServlet", value = "/schedule/ScheduleInsertServlet")
public class ScheduleInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject result = new JsonObject();


        try {

            int room = Integer.parseInt(request.getParameter("room"));
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
            String startTime = request.getParameter("startTime");
            String stringDate = request.getParameter("date");

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            ScheduleController scheduleController = new ScheduleController(connectionMaker);
            MovieController movieController = new MovieController(connectionMaker);
            MovieDTO movieDTO = movieController.selectOne(movieId);

            String endTime = scheduleController.minuteToHour(startTime, Integer.parseInt(movieDTO.getLength()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(stringDate);



            int startMinute = scheduleController.HourToMinute(startTime);
            int endMinute = scheduleController.HourToMinute(endTime);


            ArrayList<ScheduleDTO> scheduleList = scheduleController.selectAllCinemaRoom(cinemaId, room);
            for (ScheduleDTO s : scheduleList) {
                int start = scheduleController.HourToMinute(s.getStartTime());
                int end = scheduleController.HourToMinute(s.getEndTime());

                if ((start <= startMinute && startMinute <= end) || (start <= endMinute && endMinute <= end)) {

                    throw new NullPointerException();
                }
            }

            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setMovieId(movieId);
            scheduleDTO.setCinemaId(cinemaId);
            scheduleDTO.setStartTime(startTime);
            scheduleDTO.setEndTime(endTime);
            scheduleDTO.setScreeningDate(date);
            scheduleDTO.setRoom(room);

            scheduleController.insert(scheduleDTO);

            result.addProperty("status", "success");


        } catch (NullPointerException e){
            result.addProperty("status", "null");
        } catch (Exception e) {
            result.addProperty("status", "fail");
        }

        writer.print(result);

    }
}













