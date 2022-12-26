package spring.jsf.web.filter;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.jsf.web.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class UserFilter implements Filter {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserFilter.class);

    public static final String LOGIN_PAGE = "/login.xhtml";


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;
        String uri = httpServletRequest.getRequestURI();
        // managed bean name is exactly the session attribute name
        User user = (User) httpServletRequest
                .getSession().getAttribute("login");
        if(uri.contains("/secured") && user == null){
            //if user is not logged in then redirect to login page
            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath()+LOGIN_PAGE);
        } else {
            //if user is logged in
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOGGER.debug("loginFilter initialized");
    }

    @Override
    public void destroy() {
        // close resources
    }
}
