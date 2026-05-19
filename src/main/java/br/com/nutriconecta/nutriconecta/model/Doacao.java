package br.com.nutriconecta.nutriconecta.model;

import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Representa uma doação cadastrada no sistema
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título da doação (ex: "Cesta básica", "Leite em pó")
    private String titulo;

    // Descrição detalhada da doação
    @Column(columnDefinition = "TEXT") // Campo de texto longo no banco
    private String descricao;

    // Status atual da doação (ABERTA, RESERVADA, etc.)
    @Enumerated(EnumType.STRING) // Salva o nome do enum como texto
    private StatusDoacao status = StatusDoacao.ABERTA; // Valor padrão: ABERTA

    // Data limite para retirada da doação
    private LocalDate dataExpiracao;

    // Quantidade total disponível (ex: 10 kg, 5 unidades)
    @Column(nullable = false) // Campo obrigatório
    private Long quantidade;

    // Usuário que está doando (relacionamento muitos-para-um)
    @ManyToOne
    @JoinColumn(name = "id_doador") // Nome da coluna de chave estrangeira
    private Usuario doador;
}
