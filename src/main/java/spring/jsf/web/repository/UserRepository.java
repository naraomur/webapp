package spring.jsf.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.jsf.web.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1 and u.pass = ?2")
    Optional<User> findByNameAndPass(String name, String pass);

    @Query("select u from User u where u.name = ?1")
    Optional<User> findByName(String name);
}
