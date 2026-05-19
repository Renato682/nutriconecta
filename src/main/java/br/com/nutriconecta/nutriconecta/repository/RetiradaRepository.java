package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Retirada;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório da entidade Retirada
// Usado para registrar e consultar retiradas de doações
public interface RetiradaRepository extends JpaRepository<Retirada, Long> {

    // Busca a retirada associada a uma determinada solicitação
    // Útil para saber se uma solicitação já gerou uma retirada
    Retirada findBySolicitacao(Solicitacao solicitacao);
}
