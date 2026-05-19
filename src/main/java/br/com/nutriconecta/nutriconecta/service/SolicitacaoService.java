package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusSolicitacao;

import java.util.List;

// Serviço responsável pela lógica de negócio das solicitações de doação
public interface SolicitacaoService {

    // Salva uma nova solicitação ou atualiza uma existente
    Solicitacao salvar(Solicitacao solicitacao);

    // Lista todas as solicitações cadastradas
    List<Solicitacao> listarTodas();

    // Lista as solicitações feitas por uma instituição específica
    List<Solicitacao> listarPorInstituicao(Usuario instituicao);

    // Lista as solicitações relacionadas a uma doação específica
    List<Solicitacao> listarPorDoacao(Doacao doacao);

    // Altera o status de uma solicitação (PENDENTE, APROVADA, etc.)
    Solicitacao alterarStatus(Long id, StatusSolicitacao status);

    // Adiciona este método
    Solicitacao buscarPorId(Long id);
}
