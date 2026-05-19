package br.com.nutriconecta.nutriconecta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Representa o endereço de um usuário (doador, instituição, etc.)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Logradouro (rua, avenida, etc.)
    private String logradouro;

    // Número do imóvel
    private String numero;

    // Bairro
    private String bairro;

    // Cidade
    private String cidade;

    // Estado (UF)
    private String estado;

    // CEP
    private String cep;

    // Relacionamento 1-para-1 com Usuario
    @OneToOne
    @JoinColumn(name = "id_usuario") // Chave estrangeira para a tabela de usuários
    private Usuario usuario;
}
