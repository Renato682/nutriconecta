package br.com.nutriconecta.nutriconecta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Representa um usuário do sistema (doador, instituição ou admin)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome completo do usuário
    private String nome;

    // E-mail único, usado também para login
    @Column(unique = true, nullable = false)
    private String email;

    // Senha de acesso ao sistema (idealmente deve ser criptografada)
    private String senha;

    // Tipo de usuário (DOADOR, INSTITUICAO, ADMIN)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    // Telefone de contato
    private String telefone;

    // Enum interno que define os tipos de usuário
    public enum TipoUsuario {
        DOADOR,
        INSTITUICAO,
        ADMIN
    }
}
