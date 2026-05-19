package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusSolicitacao;
import br.com.nutriconecta.nutriconecta.repository.SolicitacaoRepository;
import br.com.nutriconecta.nutriconecta.service.SolicitacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementação da lógica de negócio para solicitações de doação
@Service
@RequiredArgsConstructor
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    @Override
    public Solicitacao salvar(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<Solicitacao> listarTodas() {
        return solicitacaoRepository.findAll();
    }

    @Override
    public List<Solicitacao> listarPorInstituicao(Usuario instituicao) {
        return solicitacaoRepository.findByInstituicao(instituicao);
    }

    @Override
    public List<Solicitacao> listarPorDoacao(Doacao doacao) {
        return solicitacaoRepository.findByDoacao(doacao);
    }

    @Override
    public Solicitacao alterarStatus(Long id, StatusSolicitacao status) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        solicitacao.setStatus(status);
        return solicitacaoRepository.save(solicitacao);
    }

    // Adiciona este bloco de código
    @Override
    public Solicitacao buscarPorId(Long id) {
        return solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
    }
}