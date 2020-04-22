import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/calc")
public class CalculatorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");

        int first = Integer.parseInt(request.getParameter("first"));
        int second = Integer.parseInt(request.getParameter("second"));

        String op = request.getParameter("op");

        switch (op) {
            case "add": out.println("<h1>Result: " + (first + second) + "</h1>"); break;
            case "sub": out.println("<h1>Result: " + (first - second) + "</h1>"); break;
            case "mul": out.println("<h1>Result: " + (first * second) + "</h1>"); break;
            case "div": out.println("<h1>Result: " + (first / second) + "</h1>"); break;
        }
    }
}
