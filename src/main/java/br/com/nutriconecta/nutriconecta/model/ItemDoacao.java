package br.com.nutriconecta.nutriconecta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Representa um item específico dentro de uma doação
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itens_doacao")
public class ItemDoacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantidade deste alimento na doação (ex: 2 kg, 3 unidades)
    private Double quantidade;

    // Data de validade do alimento
    private LocalDate validade;

    // Relacionamento: muitos itens pertencem a uma mesma doação
    @ManyToOne
    @JoinColumn(name = "id_doacao")
    private Doacao doacao;

    // Relacionamento: muitos itens podem referenciar o mesmo alimento cadastrado
    @ManyToOne
    @JoinColumn(name = "id_alimento")
    private Alimento alimento;
}
