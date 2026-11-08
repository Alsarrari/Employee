package com.employee.management.controller;

import com.employee.management.common.ApiResponse;
import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addEmployee(@Valid @RequestBody Employee employee) {
        Employee addEmployee=employeeService.addEmployee(employee);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new ApiResponse("Employee created successfully",addEmployee));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable Long id) {
        Employee getEmployee= employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(new ApiResponse("Employee get successfully",getEmployee));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getEmployees() {
         employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse("Employee get successfully",null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employee) {
        Employee update= employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok().body(new ApiResponse("Employee updated successfully",update));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
       return ResponseEntity.ok().body(new ApiResponse("Employee deleted successfully",null));

    }

    @GetMapping("/get-by-department")
    public ResponseEntity<ApiResponse> getEmployeesByDepartment(@RequestParam String department) {
         employeeService.getEmployeesByDepartment(department);
         return ResponseEntity.ok().body(new ApiResponse("Employee get successfully",null));
    }

    @GetMapping("/containing")
    public ResponseEntity<ApiResponse<List<Employee>>> findByNameContaining(@RequestParam String name) {
        List<Employee> containing= employeeService.findByNameContaining(name);
         return ResponseEntity.ok().body(new ApiResponse<>("Employee get successfully",containing));
    }

    @GetMapping("/greaterthan")
    public ResponseEntity<ApiResponse<List<Employee>>> findBySalaryGreaterThan(@RequestParam BigDecimal salary) {
        List<Employee> greaterthan= employeeService.findBySalaryGreaterThan(salary);
        return ResponseEntity.ok().body(new ApiResponse<>("Employee get successfully",greaterthan));
    }

    @GetMapping("/between")
    public List<Employee> findBySalaryBetween(@RequestParam BigDecimal minSalary,
                                              @RequestParam BigDecimal maxSalary) {
        return employeeService.findBySalaryBetween(minSalary, maxSalary);
    }

    @GetMapping("/active")
    public List<Employee> findByActiveTrue() {
        return employeeService.findByActiveTrue();
    }

    @GetMapping("/departments-active")
    public List<Employee> findByDepartmentAndActiveTrue(@RequestParam String department) {
        return employeeService.findByDepartmentAndActiveTrue(department);
    }

    @GetMapping("/search")
    public List<Employee> searchByDepartments(@RequestParam String department) {
        return employeeService.searchByDepartments(department);
    }

    @GetMapping("/search-by-name")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.searchByName(name);
    }

    @GetMapping("/salary-greater")
    public List<Employee> salaryGreaterThan(@RequestParam BigDecimal salary) {
        return employeeService.salaryGreaterThan(salary);
    }

    @GetMapping("/salary-between")
    public List<Employee> salaryBetween(@RequestParam BigDecimal minSalary,
                                        @RequestParam BigDecimal maxSalary) {
        return employeeService.salaryBetween(minSalary, maxSalary);
    }

    @GetMapping("/active-employee")
    public List<Employee> activeEmployees(@RequestParam Boolean active){
        return employeeService.activeEmployees(active);
    }
    //task2
    @GetMapping("/department-active")
    public List<Employee> departmentAndActive(@RequestParam String department,
                                          @RequestParam Boolean active){
        return employeeService.departmentAndActive(department, active);
    }
    @GetMapping("/department-salary")
    public List<Employee> departmentAndSalary(@RequestParam String department,
                                              @RequestParam BigDecimal salary){
        return employeeService.departmentAndSalary(department, salary);
    }
    @GetMapping("/name-active")
    public List<Employee> nameAndActive(@RequestParam String name ,
                                        @RequestParam Boolean active){
        return  employeeService.nameAndActive(name, active);
    }
    @GetMapping("/department-age-salary")
    public List<Employee> departmentAndAgeAndSalary(@RequestParam String department,
                                                    @RequestParam Integer age,
                                                    @RequestParam BigDecimal salary){
        return employeeService.departmentAndAgeAndSalary(department, age, salary);
    }
    @GetMapping("/salary-ASC")
    public List<Employee> employeesBySalaryASC(){
        return employeeService.employeesBySalaryASC();
    }
    @GetMapping("/salary-DESC")
    public List<Employee> employeesBySalaryDESC(){
        return employeeService.employeesBySalaryDESC();
    }
    @GetMapping("/by-it")
    public List<Employee> getEmployeeByIT(@RequestParam String department){
        return employeeService.getEmployeeByIT(department);
    }
    @GetMapping("/by-id")
    public List<Employee> getEmployeesBySalary(@RequestParam BigDecimal salary){
        return employeeService.getEmployeesBySalary(salary);
    }
    @GetMapping("/salary-betweenn")
    public List<Employee> searchSalaryBetween(@RequestParam BigDecimal minSalary,
                                              @RequestParam BigDecimal maxSalary){
        return employeeService.searchSalaryBetween(minSalary, maxSalary);
    }
    @GetMapping("/by-activee")
    public List<Employee> searchEmployeeByActive(@RequestParam Boolean active) {
        return employeeService.searchEmployeeByActive(active);
    }
    @GetMapping("/by-departmentss")
    public Long searchEmployeeByDepartments(@RequestParam String department) {
        return employeeService.searchEmployeeByDepartments(department);
    }
    @GetMapping("/pagination")
    public Page<Employee> getAllEmployeeByPagination(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam (defaultValue = "10") int size){
        return employeeService.getAllEmployeeByPagination(page, size);
    }
    @GetMapping("/sorted")
    public Page<Employee> getAllEmployeesSorted(@RequestParam(defaultValue = "0")int page,
                                                @RequestParam(defaultValue = "10")int size,
                                                @RequestParam(defaultValue = "ASC")String direction){
        return employeeService.getAllEmployeesSorted(page, size, direction);
    }



}
