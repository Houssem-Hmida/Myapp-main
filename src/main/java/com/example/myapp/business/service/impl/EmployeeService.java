package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEmployeeService;
import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class EmployeeService implements IEmployeeService {

    public final EmployeeRepository employeeRepository;
    @Autowired
    public final IUtilisateurService iUtilisateurService;

    @Override
    public List<Employee> getListEmployee() {
        try {
            log.info("Fetching all employees ");
            List<Employee> employees =employeeRepository.findAllByOrderByIdAsc();
            for(Employee employee :employees){
                List<String> list_profil = new ArrayList<>();
                for(Profil profil :this.iUtilisateurService.getListUtilisateurProfils(employee.getId())){
                    list_profil.add(profil.getName());
                }
                employee.setListeProfils(list_profil);
            }
            return employees;
        } catch (Exception e){
            throw new IllegalStateException("Error EmployeeService in method getListEmployee :: " + e.toString());

        }
    }

    @Override
    public Employee getEmployeeById(Long id){
        try {
            if (id == null)
                return new Employee();
            Employee e = employeeRepository.findEmployeeById(id);
            if (e == null)
                return new Employee();
            log.info("Fetching employee with id :{} ",id);

            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EmployeeService in method getEmployeeById :: " + e.toString());
        }

    }

    @Override
    public Employee getEmployeeByName(String firstname){
        try {
            if (firstname == null)
                return new Employee();
            Employee e = employeeRepository.findEmployeeByName(firstname);
            if (e == null)
                return new Employee();
            log.info("Fetching employee with firstname :{} ",firstname);

            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EmployeeService in method getEmployeeByName :: " + e.toString());
        }

    }



    @Override
    public Employee addEmployee(Employee emp) {
        try {
            Employee empNomUnique = employeeRepository.findEmployeeByName(emp.getFirstname());

            if ( empNomUnique != null)
                throw new IllegalStateException("Employee name token");

            emp.setCreationdate(new Timestamp(new Date().getTime()));
            log.info("Saving new employee {} to the databse ",emp.getFirstname());

            return employeeRepository.save(emp);
        } catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method addEmployee :: " + e.toString());
        }
    }
    @Override
    public Employee updateEmployeeById(Employee employee,Long id) {
        try {
            Employee upemp = employeeRepository.findEmployeeById(id);
            upemp.setLastname(employee.getLastname());
            upemp.setFirstname(employee.getFirstname());
            upemp.setRole(employee.getRole());
            upemp.setManager(employee.getManager());
            upemp.setManagerid(employee.getManagerid());
            upemp.setBirthdate(employee.getBirthdate());
            upemp.setAddress(employee.getAddress());
            upemp.setPhonenumber(employee.getPhonenumber());
            upemp.setProject(employee.getProject());
            upemp.setSalle(employee.getSalle());
            upemp.setGender(employee.getGender());

            upemp.setUpdatedate(new Timestamp(new Date().getTime()));
            upemp.setId(id);

            log.info("updating employee {} to the database ",employee.getFirstname());
            return employeeRepository.save(upemp);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method updateEmployeeById :: " + e.toString());
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try {
            log.info("Deleting employee with id {}  ",id);
            employeeRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EmployeeService in method deleteEmployeeById :: " + e.toString());
        }
    }
}
