package com.project.app.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Owner{

    private String nome;

    @CPF
    private String cpf;

    @Email
    private String email;

    private String telefone;


}
