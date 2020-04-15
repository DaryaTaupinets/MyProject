package servlet.filters;

import model.User;
import org.apache.commons.lang3.StringUtils;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "FilterByLogin", urlPatterns = "/login/*")
public class FilterByLogin implements Filter {

    Logger log = Logger.getLogger(FilterByLogin.class.getName());

    private static UserService userService = UserServiceImpl.getInstance();

    private static FilterByLogin filterByLogin;

    public static FilterByLogin getInstance() {
       FilterByLogin result = filterByLogin;
        if (filterByLogin != null) {
            return result;
        }
        synchronized (FilterByLogin.class) {
            if (filterByLogin == null) {
                filterByLogin = new FilterByLogin();
            }
            return filterByLogin;
        }
    }

    private boolean logged;
    private String name;
    private String password;

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean userIsLogged(String userName, String userPassword) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)) {
            return false;
        }

        User user = userService.getUserByName(userName);

        if (user == null) {
            return false;
        }
        return userPassword.equals(user.getPassword());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        User user;
        String role = "";

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User userLogin = (User) session.getAttribute("userLogin");
        String userName = userLogin.getName();
        String userPassword = userLogin.getPassword();
        setName(userName);
        setPassword(userPassword);

        try {
            if (FilterByLogin.userIsLogged(userName, userPassword)) {
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
            setLogged(true);
            response.sendRedirect("/user");
        }

        if (role.equals("admin")) {
            setLogged(true);
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
