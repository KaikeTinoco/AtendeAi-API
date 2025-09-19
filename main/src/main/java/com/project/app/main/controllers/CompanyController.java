package com.project.app.main.controllers;

import com.project.app.main.dtos.CompanyDto;
import com.project.app.main.entities.Company;
import com.project.app.main.services.CompanyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestParam Long companyId){
        return ResponseEntity.ok(service.deleteCompany(companyId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(service.findAllCompanies());
    }

    @GetMapping("/findById")
    public ResponseEntity<Company> findCompanyById(@RequestParam Long companyId){
        return ResponseEntity.ok(service.findCompanyById(companyId));
    }




}
