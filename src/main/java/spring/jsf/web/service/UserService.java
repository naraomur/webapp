package spring.jsf.web.service;

import org.springframework.stereotype.Service;
import spring.jsf.web.model.ERole;
import spring.jsf.web.model.Role;
import spring.jsf.web.model.User;
import spring.jsf.web.repository.UserRepository;
import spring.jsf.web.util.Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByNameAndPass(String name, String pass) {
        return userRepository.findByNameAndPass(name, pass);
    }

    public void save(User currentUser) {
        userRepository.saveAndFlush(currentUser);
    }

    public User login(String name, String pass) {
        return userRepository.findByNameAndPass(name, pass);
    }

    public User register(String name, String pass) {
        User user = checkUser(name);
        if (user == null) {
            String encryptPassword = Helper.md5(pass);
            //should initiate new user for new space for new data otherwise
            //user is always null and throws null pointer exception
            user = new User();
            user.setName(name);
            user.setPass(encryptPassword);
            List<Role> roles = new ArrayList<>();
            Role role = new Role();
                if (name.equals("admin")) {
                    role.setRole(ERole.ROLE_ADMIN);
                    roles.add(role);
                } else if (name.equals("moderator")) {
                    role.setRole(ERole.ROLE_MODERATOR);
                    roles.add(role);
                } else {
                    role.setRole(ERole.ROLE_USER);
                    roles.add(role);
                }
            user.setRoles(roles);
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    public User checkUser(String name) {
        return userRepository.findByName(name);
    }
}
