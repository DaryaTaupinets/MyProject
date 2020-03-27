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

@WebServlet("/edit")
public class EditFormServlet extends HttpServlet {

    private static Service service = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showEditForm(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = service.showEditForm(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, resp);
    }
}
