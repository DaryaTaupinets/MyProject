package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.AuthHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "FilterByAdminRights", urlPatterns = "/admin/*")
public class FilterByAdminRights implements Filter {

    Logger log = Logger.getLogger(FilterByAdminRights.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = UserServiceImpl.getInstance();
        AuthHelper authHelper = AuthHelper.getInstance();

        if (!authHelper.isLogged()) {
            servletRequest.getRequestDispatcher("userNotAuth.jsp").forward(servletRequest, servletResponse);
            return;
        }

        String userName = authHelper.getUserName();
        User user = null;

        try {
            user = userService.getUserByName(userName);
        } catch (Exception e) {
            log.info("Exception in method doFilter() in class FilterByAdminRights");
        }

        String role = user.getRole();

        if (role.equals("admin")) {
            authHelper.setLogged(true);
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if (role.equals("user")) {
            servletRequest.getRequestDispatcher("noAdminRights.jsp").forward(servletRequest, servletResponse);
            return;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
