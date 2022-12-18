package spring.jsf.web.service;

import org.springframework.stereotype.Service;
import spring.jsf.web.model.User;
import spring.jsf.web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByNameAndPass(String name, String pass){
        return userRepository.findByNameAndPass(name, pass);
    }

    public void save(User currentUser) {
        userRepository.saveAndFlush(currentUser);
    }

    public User login(String name, String pass){
      return userRepository.findByNameAndPass(name, pass).orElse(null);
    }

    public User checkUser(String name) {
        return userRepository.findByName(name).orElse(null);
    }
}
