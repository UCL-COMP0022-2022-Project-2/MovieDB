package ucl.comp0022.team2.web;

import redis.clients.jedis.Jedis;
import ucl.comp0022.team2.dao.impl.PopularDaoImpl;
import ucl.comp0022.team2.helper.RedisHelper;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "popular", value = "/popular")
public class Popular extends HttpServlet {

    private String message;

    public void init() {
        try {
            Jedis jedis = RedisHelper.getJedis();
            if(!jedis.exists("popular")) {
                List<HashMap.Entry<String, Double>> list = new PopularDaoImpl().getRanking();
                for (Map.Entry<String, Double> entry : list) {
                    jedis.zadd("popular", entry.getValue(), entry.getKey());
                }
            }
            Set<String> set = jedis.zrevrange("popular", 0, -1);
            message = set.toString();
            RedisHelper.closeJedis(jedis);
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