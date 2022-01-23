package ucl.comp0022.team2.web;

import ucl.comp0022.team2.dao.impl.MovieInfoDaoImpl;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MovieInfo", value = "/movie-info")
public class MovieInfo extends HttpServlet {

    private String message;

    public void init() {
        try {
            message = new MovieInfoDaoImpl().getMovieInfoByMovieId(1).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}