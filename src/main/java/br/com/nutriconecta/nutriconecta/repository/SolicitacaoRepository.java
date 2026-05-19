package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositório da entidade Solicitacao
// Representa pedidos feitos por instituições para receber doações
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    // Retorna todas as solicitações feitas por uma instituição específica
    List<Solicitacao> findByInstituicao(Usuario instituicao);

    // Retorna todas as solicitações relacionadas a uma doação específica
    List<Solicitacao> findByDoacao(Doacao doacao);
}
