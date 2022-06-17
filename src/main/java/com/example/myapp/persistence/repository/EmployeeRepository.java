package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {

    @Query("select e from Employee e where e.id=:id ")
    public Employee findEmployeeById(@Param("id") Long id);



    @Query("select e from Employee e where e.firstname=:firstname ")
    public Employee findEmployeeByName(@Param("firstname") String firstname);

    public List<Employee> findAllByOrderByIdAsc();

}
