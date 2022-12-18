package spring.jsf.web.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //@JsonManagedReference with @JsonBackReference responsible for serialization, e.g. in JSON.
    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade= CascadeType.ALL, orphanRemoval=true)
    private Set<Employee> employeeHashSet = new HashSet<>();

    public Department() {    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeeHashSet() {
        return employeeHashSet;
    }

    public void setEmployeeHashSet(Set<Employee> employeeHashSet) {
        this.employeeHashSet = employeeHashSet;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeHashSet=" + employeeHashSet +
                '}';
    }
}
