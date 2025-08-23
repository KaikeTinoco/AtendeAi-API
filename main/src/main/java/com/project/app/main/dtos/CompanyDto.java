package com.project.app.main.dtos;

import com.project.app.main.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
    private String name;
    private String email;
    private String cnpj;
    private String telefone;
    private CompanyType type;
}
