//package com.example.demo.controller;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.demo.model.Employee;
//
//@CrossOrigin(origins="http://localhost:4200/",allowedHeaders="*")
//@RestController
//public class EmployeeController {
//
//    @GetMapping("/employees")
//    public String loginEmployee(){
//        return "authenticated successfully" ;
//    }
//
//    @GetMapping("/getEmployees")
//    public List<Employee> getEmployees(){
//        return Stream.of(new Employee(108,"Santosh","santosh@gmail.com","pass1"),
//                new Employee(101,"Basant","basant@gmail.com","pass1")).
//                collect(Collectors.toList());
//    }
//
//}
