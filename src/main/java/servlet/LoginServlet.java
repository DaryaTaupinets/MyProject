package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import servlet.filters.FilterByLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class.getName());
    private static FilterByLogin filterByLogin = FilterByLogin.getInstance();
    private static UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index");
        try {
            requestDispatcher.forward(req, resp);
        }catch (ServletException|IOException e) {
            logger.info("Exception in class LoginServlet in method doGet()");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User userLogin = null;

        if (filterByLogin.isLogged()){
            userLogin = service.getUserByName(name);
        }

        req.getSession().setAttribute("userLogin", userLogin);

        resp.sendRedirect("admin");

    }
}
