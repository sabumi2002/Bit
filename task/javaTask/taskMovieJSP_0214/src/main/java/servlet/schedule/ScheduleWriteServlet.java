package servlet.schedule;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.CinemaController;
import controller.MovieController;
import model.CinemaDTO;
import model.MovieDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ScheduleWriteServlet", value = "/schedule/ScheduleWriteServlet")
public class ScheduleWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject result = new JsonObject();


        try{
            if(request.getParameter("id")==null){
                int movieId = Integer.parseInt(request.getParameter("movieId"));
                ConnectionMaker connectionMaker = new MySqlConnectionMaker();
                MovieController movieController = new MovieController(connectionMaker);
                MovieDTO m = movieController.selectOne(movieId);
                result.addProperty("movieImg", m.getImg());
                throw new NullPointerException();
            }

            int id = Integer.parseInt(request.getParameter("id"));
            String movieRank = request.getParameter("movieRank");



            ConnectionMaker connectionMaker= new MySqlConnectionMaker();
            CinemaController cinemaController = new CinemaController(connectionMaker);
            MovieController movieController = new MovieController(connectionMaker);

            CinemaDTO cinemaDTO = cinemaController.selectOne(id);
            ArrayList<MovieDTO> list = movieController.selectAll(movieRank);

            JsonArray movieList = new JsonArray();

            for (MovieDTO m : list){
                JsonObject object =new JsonObject();
                object.addProperty("id", m.getId());
                object.addProperty("img", m.getImg());
                object.addProperty("title", m.getTitle());

                movieList.add(object);
            }

            result.addProperty("room", cinemaDTO.getRoom());
            result.addProperty("movieList", movieList.toString());
            result.addProperty("status", "success");

        } catch (NullPointerException e){
            result.addProperty("status", "fail");
        } catch (Exception e){
            result.addProperty("status", "fail");
        }

        writer.print(result);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
