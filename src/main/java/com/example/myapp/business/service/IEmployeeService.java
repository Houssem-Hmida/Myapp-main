package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> getListEmployee();
    public Employee getEmployeeById(Long id);

    Employee getEmployeeByName(String firstname);

    public Employee addEmployee(Employee emp);
    public void deleteEmployeeById(Long id);
    public Employee updateEmployeeById(Employee employee,Long id);
}
