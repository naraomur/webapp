package spring.jsf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
    private Long id;
    private String name;
    private List<Employee> employeeHashSet;
}
