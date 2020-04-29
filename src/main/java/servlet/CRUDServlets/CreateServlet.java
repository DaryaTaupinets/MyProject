package servlet.CRUDServlets;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/create")
public class CreateServlet extends HttpServlet {

    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Byte age = Byte.parseByte(req.getParameter("age"));
        String email = req.getParameter("email");
        String location = req.getParameter("location");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        userService.createUser(new User(name, age, email, location, password, role));
        resp.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
    }
}
