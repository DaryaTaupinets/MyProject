package servlet.filters;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "FilterByAdminRights", urlPatterns = "/admin/*")
public class FilterByAdminRights implements Filter {

    Logger log = Logger.getLogger(FilterByAdminRights.class.getName());

    private static UserService userService = UserServiceImpl.getInstance();

    private static FilterByLogin filterByLogin = FilterByLogin.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!filterByLogin.isLogged()) {
            servletRequest.getRequestDispatcher("userNotAuth.jsp").forward(servletRequest, servletResponse);
            return;
        }

        String userName = filterByLogin.getName();
        User user = null;

        try {
            user = userService.getUserByName(userName);
        } catch (Exception e) {
            log.info("Exception in method doFilter() in class FilterByAdminRights");
        }

        String role = user.getRole();

        if (role.equals("admin")) {
            filterByLogin.setLogged(true);
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
