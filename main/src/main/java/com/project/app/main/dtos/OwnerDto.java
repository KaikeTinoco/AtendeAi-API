package com.project.app.main.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String cnpj;
}
