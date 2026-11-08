package com.employee.management.service;
import com.employee.management.entity.Employee;
import com.employee.management.exception.DuplicateEmailException;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
       if (employeeRepository.existsByEmail(employee.getEmail())){
           throw new DuplicateEmailException("Email already exists!");
       }
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id){
      return employeeRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
       }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee updateEmployee(Long id , Employee employee ){

      Employee update= employeeRepository.findById(id)
                      .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
      Optional<Employee> existingEmployee= employeeRepository.findByEmail(employee.getEmail());

      if (existingEmployee.isPresent()&& !existingEmployee.get().getId().equals(id)){
          throw new DuplicateEmailException("Email already exists");
      }
      update.setName(employee.getName());
      update.setAge(employee.getAge());
      update.setEmail(employee.getEmail());
      update.setSalary(employee.getSalary());
      update.setDepartment(employee.getDepartment());
      update.setActive(employee.getActive());
      return employeeRepository.save(update);
    }
    public void deleteEmployee(Long id){
        employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
          employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesByDepartment(String department){
        return employeeRepository.findByDepartment(department);
    }
    public List<Employee> findByNameContaining(String name){
        return employeeRepository.findByNameContaining(name);
    }
    public List<Employee> findBySalaryGreaterThan(BigDecimal salary){
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
    public List<Employee> findBySalaryBetween(BigDecimal minSalary,
                                              BigDecimal maxSalary){
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }
    public List<Employee> findByActiveTrue(){
        return employeeRepository.findByActiveTrue();
    }
    public List<Employee> findByDepartmentAndActiveTrue(String department){
        return employeeRepository.findByDepartmentAndActiveTrue(department);
    }
    public List<Employee> searchByDepartments(String department){
        return employeeRepository.searchByDepartments(department);
    }
    public List<Employee> searchByName(String name){
        return employeeRepository.searchByName(name);
    }
    public List<Employee> salaryGreaterThan(BigDecimal salary){
        return employeeRepository.salaryGreaterThan(salary);
    }
    public List<Employee> salaryBetween(BigDecimal minSalary,BigDecimal maxSalary){
        return employeeRepository.salaryBetween(minSalary, maxSalary);
    }
    public List<Employee> activeEmployees(Boolean active){
        return employeeRepository.activeEmployees(active);
    }
    public List<Employee> departmentAndActive(String department,Boolean active){
        return employeeRepository.departmentAndActive(department, active);
    }
    public List<Employee> departmentAndSalary(String department,BigDecimal salary){
        return employeeRepository.departmentAndSalary(department, salary);
    }
    public List<Employee> nameAndActive(String name , Boolean active){
        return employeeRepository.nameAndActive(name, active);
    }
    public List<Employee> departmentAndAgeAndSalary(String department,
                                                    Integer age,
                                                    BigDecimal salary){
        return employeeRepository.departmentAndAgeAndSalary(department, age, salary);
    }
    public List<Employee> employeesBySalaryASC(){
        return employeeRepository.employeesBySalaryASC();
    }
    public List<Employee> employeesBySalaryDESC(){
        return employeeRepository.employeesBySalaryDESC();
    }
    public List<Employee> getEmployeeByIT (String department){
        return employeeRepository.getEmployeeByIT(department);
    }
    public List<Employee> getEmployeesBySalary(BigDecimal salary){
        return employeeRepository.getEmployeesBySalary(salary);
    }
    public List<Employee> searchSalaryBetween(BigDecimal minSalary,
                                              BigDecimal maxSalary){
        return employeeRepository.searchSalaryBetween(minSalary, maxSalary);
    }
    public List<Employee> searchEmployeeByActive(Boolean active) {
        return employeeRepository.searchEmployeeByActive(active);
    }
    public Long searchEmployeeByDepartments(String department) {
        return employeeRepository.searchEmployeeByDepartments(department);
    }
    public Page<Employee> getAllEmployeeByPagination(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return employeeRepository.findAll(pageable);
    }
    public Page<Employee> getAllEmployeesSorted(int page,int size,String direction ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), "salary");
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }
}
