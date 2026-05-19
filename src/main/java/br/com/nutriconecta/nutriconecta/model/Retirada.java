package br.com.nutriconecta.nutriconecta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Representa o registro da retirada de uma doação por uma instituição
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retiradas")
public class Retirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data e hora em que a retirada foi realizada
    private LocalDateTime dataRetirada;

    // Nome da pessoa responsável pela retirada
    private String responsavel;

    // Observações adicionais sobre a retirada
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Relacionamento 1-para-1 com a solicitação que originou a retirada
    @OneToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao solicitacao;
}

