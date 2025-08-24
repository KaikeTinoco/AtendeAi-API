package com.project.app.main.entities;

import com.project.app.main.enums.CompanyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
@Builder
public class Company extends Owner{

    private String name;

    @CNPJ
    private String cnpj;

    private String email;

    private String telefone;

    private CompanyType type;
}

