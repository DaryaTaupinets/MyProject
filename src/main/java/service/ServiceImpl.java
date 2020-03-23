package service;

import dao.UserJdbcDAO;
import model.User;
import servlet.CreateServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public final class ServiceImpl implements Service {

    private static ServiceImpl serviceImpl;

    private ServiceImpl() {
    }

    public static ServiceImpl getInstance() {
        ServiceImpl result = serviceImpl;
        if (serviceImpl != null) {
            return result;
        }
        synchronized (ServiceImpl.class) {
            if (serviceImpl == null) {
                serviceImpl = new ServiceImpl();
            }
            return serviceImpl;
        }
    }

    private static final Logger log = Logger.getLogger(CreateServlet.class.getName());

    private UserJdbcDAO userJdbcDAO = new UserJdbcDAO();

    @Override
    public void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        List<User> userList = userJdbcDAO.selectAllUsers();
        req.setAttribute("userList", userList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        byte age = Byte.parseByte(req.getParameter("age"));
        String email = req.getParameter("email");
        String location = req.getParameter("location");

        User user = new User(id, name, age, email, location);
        userJdbcDAO.updateUser(user);
        resp.sendRedirect("list");
    }

    @Override
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userJdbcDAO.deleteUserById(id);
        resp.sendRedirect("list");
    }

    @Override
    public void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userJdbcDAO.selectUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, resp);
    }

    @Override
    public void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String name = req.getParameter("name");
        byte age = Byte.parseByte(req.getParameter("age"));
        String email = req.getParameter("email");
        String location = req.getParameter("location");
        User newUser = new User(name, age, email, location);
        userJdbcDAO.insertUser(newUser);
        resp.sendRedirect("list");
    }
}
