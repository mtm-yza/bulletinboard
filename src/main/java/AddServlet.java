import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        int number1 = Integer.parseInt(req.getParameter("num1"));
        int number2 = Integer.parseInt(req.getParameter("num2"));

        int output = number1 + number2;
        res.getWriter().println("Result: " + output);
    }
}