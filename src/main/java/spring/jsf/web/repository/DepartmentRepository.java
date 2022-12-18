package spring.jsf.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jsf.web.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
