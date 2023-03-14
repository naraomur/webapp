package spring.jsf.web.filter;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.jsf.web.model.ERole;
import spring.jsf.web.model.Role;
import spring.jsf.web.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@NoArgsConstructor
@Component
public class UserFilter implements Filter {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserFilter.class);

    public static final String LOGIN_PAGE = "/login.xhtml";
    public static final String NO_ACCESS_PAGE = "/noaccess.xhtml";
    public static final String HOME_PAGE = "/secured/home.xhtml";

    public static final String ADMIN_PAGE = "/admin/admin.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + LOGIN_PAGE;

        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }

//        HttpServletRequest httpServletRequest =
//                (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse =
//                (HttpServletResponse) servletResponse;
//        String uri = httpServletRequest.getRequestURI();
//        // managed bean name is exactly the session attribute name
//        User user = (User) httpServletRequest
//                .getSession().getAttribute("login");
//        if (uri.contains("/admin") && !user.getRoles().contains(new Role(ERole.ROLE_ADMIN))) {
//            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + NO_ACCESS_PAGE);
//        } else if (user != null && uri.contains("/login")) {
//            if (user.getRoles().contains(new Role(ERole.ROLE_ADMIN))) {
//                httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + ADMIN_PAGE);
//            }
//            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + HOME_PAGE);
//        } else {
//            //if admin is logged in then redirect to admin page
//            //if user is logged in then redirect to home page
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
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
