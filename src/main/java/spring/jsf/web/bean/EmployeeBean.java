package spring.jsf.web.bean;

import spring.jsf.web.controller.okHttp;
import spring.jsf.web.dto.Department;
import spring.jsf.web.model.Employee;
import spring.jsf.web.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@ViewScoped
public class EmployeeBean {
    private static final long serialVersionUID = 1L;
    private final EmployeeService employeeService;
    private List<Employee> employees;
    private List<spring.jsf.web.dto.Employee> depEmployees;
    private Employee employee = new Employee();
    private Long depId;
    private Department department;

    public EmployeeBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostConstruct
    public void load() {
        employees = employeeService.getAllEmployees();
    }
    //    public void empByDepId() {
//        depEmployees = employeeRepository.findAllByDepartmentId(depId);
//    }
    public void loadDepById() throws IOException {
        department = okHttp.getDepById(depId);
        depEmployees = department.getEmployeeHashSet();
    }

    public Long getDepId() {
        return depId;
    }
    public void setDepId(Long depId) {
        this.depId = depId;
    }
    public void setDepEmployees(List<spring.jsf.web.dto.Employee> depEmployees){
        this.depEmployees = depEmployees;
    }
    public List<spring.jsf.web.dto.Employee> getDepEmployees() {
        return depEmployees;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return employee;
    }
}
