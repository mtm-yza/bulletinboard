import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/square")
public class SqServlet  extends HttpServlet {
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        int k = (int) req.getAttribute("k");
        k = k * k;
        res.getWriter().println("Result: " + k);
    }
}
