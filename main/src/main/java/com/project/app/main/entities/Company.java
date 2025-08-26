package com.project.app.main.entities;

import com.project.app.main.enums.CompanyType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CNPJ;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Company extends Owner{
    @CNPJ
    private String cnpj;

    private CompanyType type;
}

