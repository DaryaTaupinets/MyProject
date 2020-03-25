package servlet;

import model.User;
import service.Service;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list")
public class ReadServlet extends HttpServlet {

    private static Service service = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            listUser(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException, SQLException {
        List<User> userList = service.listUser();
        req.setAttribute("userList", userList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, resp);
    }
}
