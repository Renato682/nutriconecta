package br.com.nutriconecta.nutriconecta.model;

import br.com.nutriconecta.nutriconecta.model.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Representa uma solicitação de doação feita por uma instituição
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data e hora em que a solicitação foi feita
    // Inicializada com o momento atual por padrão
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    // Status da solicitação (PENDENTE, APROVADA, etc.)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;

    // Doação à qual esta solicitação está vinculada
    @ManyToOne
    @JoinColumn(name = "id_doacao")
    private Doacao doacao;

    // Instituição que fez a solicitação (usuário do tipo INSTITUICAO)
    @ManyToOne
    @JoinColumn(name = "id_instituicao")
    private Usuario instituicao;

    // Relacionamento bidirecional: permite a Solicitação saber se a retirada já aconteceu
    @OneToOne(mappedBy = "solicitacao")
    private Retirada retirada;
}
