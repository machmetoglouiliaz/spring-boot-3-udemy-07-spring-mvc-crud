package com.mourat.udemy.employeesdemo.controller;

import com.mourat.udemy.employeesdemo.dao.EmployeeJPARepository;
import com.mourat.udemy.employeesdemo.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeJPARepository repo;

    public EmployeeController(EmployeeJPARepository repo){
        this.repo = repo;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){

        List<Employee> employees = repo.findAll();

        model.addAttribute("employees", employees);

        return "list-employees";
    }

    @GetMapping("/showEmployeeForm")
    public String showForm(Model model){

        model.addAttribute("employee", new Employee());

        return "employee-form";
    }

    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        repo.save(employee);

        return "redirect:/employees/list";
    }
}
