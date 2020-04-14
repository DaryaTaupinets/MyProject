package servlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterByUserRights", urlPatterns = "/user/*")
public class FilterByUserRights implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterByLogin filterByLogin = FilterByLogin.getInstance();

        if (!filterByLogin.isLogged()) {
            servletRequest.getRequestDispatcher("userNotAuth.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
