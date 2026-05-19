package br.com.nutriconecta.nutriconecta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data: gera getters, setters, toString, equals e hashCode automaticamente (Lombok)
// @NoArgsConstructor: gera construtor sem argumentos
// @AllArgsConstructor: gera construtor com todos os argumentos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indica que esta classe é uma entidade JPA (tabela no banco)
@Table(name = "alimentos") // Nome da tabela no banco de dados
public class Alimento {

    @Id // Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento no banco
    private Long id;

    // Nome do alimento (ex: Arroz, Feijão, Leite)
    private String nome;

    // Categoria do alimento (ex: Perecível, Não perecível, Hortifruti)
    private String categoria;

    // Unidade de medida (ex: kg, L, un)
    private String unidadeMedida;
}
