package com.employee.management.repository;

import com.employee.management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
    // Query Methods
    List<Employee> findByDepartment (String department);
    List<Employee> findByNameContaining(String name);
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);
    List<Employee> findBySalaryBetween(BigDecimal minSalary , BigDecimal maxSalary);
    List<Employee> findByActiveTrue();
    List<Employee> findByDepartmentAndActiveTrue(String department);
    // JPQL
    @Query("select e from Employee e where e.department =:department")
    List<Employee> searchByDepartments(@Param("department") String department);
    @Query("select e from Employee e where e.name =:name")
    List<Employee> searchByName(@Param("name") String name);
    @Query("select e from Employee e where e.salary >:salary ")
    List<Employee> salaryGreaterThan(@Param("salary") BigDecimal salary);
    @Query("select e from Employee e where e.salary between :minSalary and :maxSalary  ")
    List<Employee> salaryBetween(@Param("minSalary")BigDecimal minSalary,
                                 @Param("maxSalary") BigDecimal maxSalary);
    @Query("select e from Employee e where e.active =:active")
    List<Employee> activeEmployees(@Param("active") Boolean active);
    //task 2
    @Query("select e from Employee e where e.department =:department and e.active =:active")
    List<Employee> departmentAndActive(@Param("department")String department,
                                       @Param("active")Boolean active);
    @Query("select e from Employee e where e.department =:department and e.salary =:salary")
    List<Employee> departmentAndSalary(@Param("department")String department,
                                       @Param("salary")BigDecimal salary);
    @Query("select e from Employee e where e.name =:name and e.active =:active")
    List<Employee> nameAndActive(@Param("name")String name,
                                 @Param("active")Boolean active);
    @Query("select e from Employee e where e.department =:department and e.age =:age and e.salary =:salary")
    List<Employee> departmentAndAgeAndSalary(@Param("department")String department,
                                             @Param("age")Integer age,
                                             @Param("salary")BigDecimal salary);
    //task3
    @Query("select e from Employee e  order by e.salary ASC ")
    List<Employee> employeesBySalaryASC();
    @Query("select e from Employee e order by e.salary DESC")
    List<Employee> employeesBySalaryDESC();

    //SQL Native
    @Query(value = "select * from employees where department =:department ",nativeQuery = true)
    List<Employee> getEmployeeByIT(@Param("department")String department);
    @Query(value = "select * from employees where salary >:salary",nativeQuery = true)
    List<Employee> getEmployeesBySalary(@Param("salary")BigDecimal salary);
    @Query(value = "select * from employees where salary between :minSalary and :maxSalary ",nativeQuery = true)
    List<Employee> searchSalaryBetween(@Param("minSalary")BigDecimal minSalary,
                                       @Param("maxSalary")BigDecimal maxSalary);
    @Query(value = "select * from employees where active =:active",nativeQuery = true)
    List<Employee> searchEmployeeByActive(@Param("active")Boolean active);
    @Query(value = "select count(*) from employees where department =:department ",nativeQuery = true)
    Long searchEmployeeByDepartments(@Param("department")String department);

    //Pagination
    Page<Employee> findAll(Pageable pageable);

}
