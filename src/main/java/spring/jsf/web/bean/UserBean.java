package spring.jsf.web.bean;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.jsf.web.model.ERole;
import spring.jsf.web.model.User;
import spring.jsf.web.service.UserService;
import spring.jsf.web.util.Helper;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@NoArgsConstructor
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserBean.class);
    public static final String HOME_PAGE_REDIRECT =
            "/secured/home.xhtml?faces-redirect=true";
    public static final String ADMIN_PAGE_REDIRECT =
            "/admin/admin.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT =
            "/logout.xhtml?faces-redirect=true";
    private User user = new User();
    private String userId;
    private String userPassword;
    private List<User> users;
    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        users = userService.findAll();
    }
    public String login(){
        String password = Helper.md5(userPassword);
        user = userService.login(userId, password);
        if(user != null){
            HttpSession session = (HttpSession) Helper.getFacesContext()
                    .getExternalContext()
                    .getSession(true);
            session.setAttribute("login", user);
            LOGGER.info("login successful for '{}'", userId);
            // check the role
            if(userService.isAdmin(user)){
                return ADMIN_PAGE_REDIRECT;
            }
            return HOME_PAGE_REDIRECT;
        } else {
            String msg = Helper.getResourceBundle("text").getString("msgNotFound");
            Helper.getFacesContext().addMessage(null, new FacesMessage(msg));
            LOGGER.info("login failed for '{}'", userId);
            return null;
        }
    }
    public String register(){
        user = userService.checkUser(userId);
        if(user != null){
            String summary = Helper.getResourceBundle("text").getString("msgAlreadyRegister");
            Helper.getFacesContext().addMessage(null, new FacesMessage(summary));
            return "register";
        } else {
            //should initiate new user for new space for new data otherwise
            //user is always null and throws null pointer exception
            user = new User();
            user = userService.register(userId, userPassword);
        }
        return "login";
    }

    public String logout() throws IOException {
        LOGGER.info("logout successful for '{}'", userId);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        user = null;
        return LOGOUT_PAGE_REDIRECT;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

//    public boolean isLoggedIn() {
//        return user != null;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
