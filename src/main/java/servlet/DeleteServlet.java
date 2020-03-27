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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    //private static Service service = UserJdbcService.getInstance();
    private static Service service = UserHibernateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            deleteUser(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteUser(id);
        resp.sendRedirect("list");
    }
}
