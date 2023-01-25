package spring.jsf.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.jsf.web.model.ERole;
import spring.jsf.web.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.role = ?1")
    Role findByName(ERole name);
}
