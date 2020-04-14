package servlet;

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
        FilterByLogin filterByLogin = FilterByLogin.getInstance();
        String userName = filterByLogin.getName();

        filterByLogin.setLogged(false);
        filterByLogin.setName("");
        filterByLogin.setPassword("");

        req.setAttribute("name", userName);
        req.getRequestDispatcher("logout.jsp").forward(req, resp);
    }
}
