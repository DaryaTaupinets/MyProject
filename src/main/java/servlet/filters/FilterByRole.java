package servlet.filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "FilterByRole", urlPatterns = {"/admin/*","/create", "/delete", "/update"})
public class FilterByRole implements Filter {

    Logger log = Logger.getLogger(FilterByRole.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User userLogin = (User) session.getAttribute("userLogin");
        String userRole = userLogin.getRole();

        if (userRole.equals("admin")) {
            filterChain.doFilter(request, response);
        } else if (userRole.equals("user")) {
            request.setAttribute("userLogin", userLogin);
            response.sendRedirect("user");
        } else if (userRole.isEmpty()) {
            response.sendRedirect("mistake");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
