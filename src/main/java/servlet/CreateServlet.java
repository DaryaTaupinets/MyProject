package servlet;

import service.Service;
import service.UserHibernateService;
import service.UserJdbcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insert")
public class CreateServlet extends HttpServlet {

    //private static Service service = UserJdbcService.getInstance();
    private static Service service = UserHibernateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            insertUser(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String name = req.getParameter("name");
        byte age = Byte.parseByte(req.getParameter("age"));
        String email = req.getParameter("email");
        String location = req.getParameter("location");
        service.insertUser(name, age, email, location);
        resp.sendRedirect("list");
    }
}
