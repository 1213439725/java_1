import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete")
public class delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String table_name=req.getParameter("table_name");
        String table_property=req.getParameter("table_property");
        Jedis jedis =new Jedis("localhost");
        long number=jedis.hdel(table_name,table_property);
        jedis.close();
        String html=null;
        if(number==0){
            html= "<div>fail</div></br>" +
                    "<button type=\"submit\" value=\"\" onclick=\"window.location.href = 'http://localhost:8080/delete.html'\">return</button>";
        }
        else {
            html="<div>success</div></br>" +
                    "<button type=\"submit\" value=\"\" onclick=\"window.location.href = 'http://localhost:8080/delete.html'\">return</button>";
        }
        PrintWriter pw =resp.getWriter();
        pw.println(html);
    }
}
