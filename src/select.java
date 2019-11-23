import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/select")
public class select extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String table_name = req.getParameter("table_name");
        Jedis jedis =new Jedis("localhost");
        Map table=jedis.hgetAll(table_name);
        String html = "<div style='color:black'>"+table+
                "</div><br/><button type=\"submit\" value=\"select\" onclick=\"window.location.href = 'http://localhost:8080/select.html'\">return</button";
        PrintWriter pw =resp.getWriter();
        pw.println(html);
        jedis.close();
    }

}