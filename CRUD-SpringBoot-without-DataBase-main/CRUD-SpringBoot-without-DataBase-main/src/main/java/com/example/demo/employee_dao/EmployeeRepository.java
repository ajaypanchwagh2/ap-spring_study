package com.example.demo.employee_dao;

import com.example.demo.employee_model.EmployeeModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
    static private Map<Integer, EmployeeModel> employees = new HashMap<>();

    {
        EmployeeModel employee1 = new EmployeeModel(1, "Prashant", "9956823659", 50000);
        EmployeeModel employee2 = new EmployeeModel(2, "Bill", "9000256893", 25000);
        EmployeeModel employee3 = new EmployeeModel(3, "Hyderabad", "9254684789", 35000);
        employees.put(employee1.getEmpId(), employee1);
        employees.put(employee2.getEmpId(), employee2);
        employees.put(employee3.getEmpId(), employee3);
    }

    @Override
    public List<EmployeeModel> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public EmployeeModel getEmployeeDetails(Integer empId) {
        if (employees.containsKey(empId)) {
            return employees.get(empId);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeModel addEmployee(EmployeeModel employee) {
        if (!employees.containsKey(employee.getEmpId())) {
            employees.put(employee.getEmpId(), employee);
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeModel updateEmployee(EmployeeModel employee) {
        if (employees.containsKey(employee.getEmpId())) {
            employees.put(employee.getEmpId(), employee);
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public String deleteEmployeeDetails(Integer empId) {
        if (employees.containsKey(empId)) {
            employees.remove(empId);
            return "DELETED SUCCESSFULLY";
        } else {
            return null;
        }
    }
}
