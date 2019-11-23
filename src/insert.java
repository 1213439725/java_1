import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/insert")
public class insert extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String table_name =req.getParameter("table_name");
        String table_property =req.getParameter("table_property");
        String table_value =req.getParameter("table_value");
        Jedis jedis = new Jedis("localhost");
        long success=jedis.hsetnx(table_name,table_property,table_property);
        if(success==1){
            String htmlsuccess="<div>success</div></br>"+
                    "<button type=\"submit\" value=\"\" onclick=\"window.location.href = 'http://localhost:8080/insert.html'\">return</button>";
            jedis.close();
            PrintWriter pw =resp.getWriter();
            pw.println(htmlsuccess);
        }
        else if(success==0){
            jedis.close();
            String htmlfail="<div>fail</div></br>"+
                    "<button type=\"submit\" value=\"\" onclick=\"window.location.href = 'http://localhost:8080/insert.html'\">return</button>";
            PrintWriter pw =resp.getWriter();
            pw.println(htmlfail);
        }
    }
}
