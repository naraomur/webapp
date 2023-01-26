package spring.jsf.web.service;

import org.springframework.stereotype.Service;
import spring.jsf.web.model.ERole;
import spring.jsf.web.model.Role;
import spring.jsf.web.model.User;
import spring.jsf.web.repository.UserRepository;
import spring.jsf.web.util.Helper;

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
            if(user.getRoles() == null){
                if (name.equals("admin")) {
                    roles.add(new Role(ERole.ROLE_ADMIN));
                } else if (name.equals("moderator")) {
                    roles.add(new Role(ERole.ROLE_MODERATOR));
                } else {
                    roles.add(new Role(ERole.ROLE_USER));
                }
            }
            user.setRoles(roles);
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    public User checkUser(String name) {
        return userRepository.findByName(name);
    }

    public boolean isAdmin(User user) {
        // Use a JPA repository to retrieve the user's roles from the database
        List<Role> roles = userRepository.findUserRoleById(user.getId());
        int size = roles.size();
        // Check if the user has the "admin" role
        return roles.contains(ERole.ROLE_ADMIN);
    }
}
