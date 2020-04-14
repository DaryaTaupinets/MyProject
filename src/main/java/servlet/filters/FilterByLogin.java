package servlet.filters;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.AuthHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "FilterByLogin", urlPatterns = "/login/*")
public class FilterByLogin implements Filter {

    Logger log = Logger.getLogger(FilterByLogin.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = UserServiceImpl.getInstance();
        AuthHelper authHelper = AuthHelper.getInstance();
        User user;
        String role = "";

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String userName = request.getParameter("name");
        String userPassword = request.getParameter("password");
        authHelper.setUserName(userName);
        authHelper.setUserPassword(userPassword);

        try {
            if (AuthHelper.userIsLogged(userName, userPassword)) {
                user = userService.getUserByName(userName);
                role = user.getRole();
            } else {
                request.getRequestDispatcher("mistakeAuth.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            log.info("Exception in method doFilter() in class FilterByLogin");
        }

        if (role.equals("user")) {
            authHelper.setLogged(true);
            response.sendRedirect("/user");
        }

        if (role.equals("admin")) {
            authHelper.setLogged(true);
            response.sendRedirect("/admin");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
