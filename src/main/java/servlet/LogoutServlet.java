package servlet;

import model.User;
import servlet.filters.FilterByLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userLogin");
        String userName = user.getName();
        req.setAttribute("name", userName);
        req.getRequestDispatcher("logout.jsp").forward(req, resp);
    }
}
