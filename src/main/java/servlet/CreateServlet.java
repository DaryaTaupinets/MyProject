package servlet;

import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class CreateServlet extends HttpServlet {

    static final ServiceImpl service = ServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = req.getServletPath();

        try {
            switch (option) {
                case "/new":
                    service.showNewForm(req, resp);
                    break;
                case "/insert":
                    service.insertUser(req, resp);
                    break;
                case "/delete":
                    service.deleteUser(req, resp);
                    break;
                case "/edit":
                    service.showEditForm(req, resp);
                    break;
                case "/update":
                    service.updateUser(req, resp);
                    break;
                default:
                    service.listUser(req, resp);
                    break;
            }
        } catch (SQLException | ServletException e) {
           e.printStackTrace();
        }
    }
}
