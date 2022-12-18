package spring.jsf.web.filter;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.jsf.web.bean.UserBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class UserFilter implements Filter {
    @Inject
    private static UserBean toGet;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserFilter.class);

    public static final String LOGIN_PAGE = "/login.xhtml";
    public static final String LOGOUT_PAGE = "/logout.xhtml";


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
        UserBean userBean = (UserBean) httpServletRequest
                .getSession().getAttribute("user");
        if(uri.contains("/secured") && userBean == null){
            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath()+LOGIN_PAGE);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
       /* if (userBean.getUser() != null) {
                LOGGER.debug("user is logged in");
                // user is logged in, continue request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath() + LOGIN_PAGE);
            }*/
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
