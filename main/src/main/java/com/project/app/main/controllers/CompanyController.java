package com.project.app.main.controllers;

import com.project.app.main.dtos.CompanyDto;
import com.project.app.main.entities.Company;
import com.project.app.main.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerCompany(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDto dto,
                                                 @RequestParam Long companyId){
        return ResponseEntity.ok(service.updateCompany(companyId, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestParam Long companyId){
        return ResponseEntity.ok(service.deleteCompany(companyId));
    }





}
