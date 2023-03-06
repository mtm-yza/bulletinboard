import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

    int number1 = Integer.parseInt(req.getParameter("num1"));
    int number2 = Integer.parseInt(req.getParameter("num2"));

    int output = number1 + number2;

    RequestDispatcher dispatcher = req.getRequestDispatcher("square");
    req.setAttribute("k", output);    
    dispatcher.forward(req, res);
  } 
}