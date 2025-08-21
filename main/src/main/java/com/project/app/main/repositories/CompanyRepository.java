package com.project.app.main.repositories;

import com.project.app.main.entities.Company;
import com.project.app.main.enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {
    Optional<Company> findById (Long companyId);
    Optional<Company> findByCnpj (String cnpj);
    Optional<Company> findByName (String name);
    Optional<Company> findByType (CompanyType type);

}
