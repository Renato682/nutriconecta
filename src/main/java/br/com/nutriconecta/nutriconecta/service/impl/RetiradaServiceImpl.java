package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Retirada;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.repository.RetiradaRepository;
import br.com.nutriconecta.nutriconecta.service.RetiradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Implementação da lógica de negócio para retiradas de doações
@Service
@RequiredArgsConstructor
public class RetiradaServiceImpl implements RetiradaService {

    private final RetiradaRepository retiradaRepository;

    // Registra uma nova retirada no banco de dados
    @Override
    public Retirada registrar(Retirada retirada) {
        return retiradaRepository.save(retirada);
    }

    // Busca a retirada associada a uma solicitação específica
    @Override
    public Retirada buscarPorSolicitacao(Solicitacao solicitacao) {
        return retiradaRepository.findBySolicitacao(solicitacao);
    }

    @Override
    public java.util.List<Retirada> listarTodas() {
        return retiradaRepository.findAll();
    }

}
