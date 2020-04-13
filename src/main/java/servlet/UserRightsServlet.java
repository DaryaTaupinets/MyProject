package servlet;

import util.AuthHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserRightsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthHelper authHelper = AuthHelper.getInstance();
        String userName = authHelper.getUserName();

        req.setAttribute("name", userName);
        req.getRequestDispatcher("user-page.jsp").forward(req, resp);
    }
}
