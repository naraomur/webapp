package spring.jsf.web.service;

import org.springframework.stereotype.Service;
import spring.jsf.web.model.Employee;
import spring.jsf.web.repository.EmployeeRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //read
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployees(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public void deleteEmployees(Long id) {
        employeeRepository.deleteById(id);
    }
}
