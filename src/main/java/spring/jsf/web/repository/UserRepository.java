package spring.jsf.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.jsf.web.model.Role;
import spring.jsf.web.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1 and u.pass = ?2")
    User findByNameAndPass(String name, String pass);

    @Query("select u from User u where u.name = ?1")
    User findByName(String name);

    @Query("select u from User u join u.roles r WHERE NOT EXISTS" + "(SELECT r FROM u.roles WHERE r.role = 'ROLE_ADMIN')")
    List<User> getUsers();

    @Query("select u from User u join fetch u.roles r where r.id = :id")
    List<Role> findUserRoleById(Long id);
}
