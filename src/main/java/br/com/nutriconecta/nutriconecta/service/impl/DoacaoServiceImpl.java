package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;
import br.com.nutriconecta.nutriconecta.repository.DoacaoRepository;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementação da lógica de negócio para doações
@Service
@RequiredArgsConstructor
public class DoacaoServiceImpl implements DoacaoService {

    private final DoacaoRepository doacaoRepository;

    @Override
    public long count() {
        return doacaoRepository.count();
    }

    @Override
    public long totalAlimentosDistribuidos() {
        // Certifique-se de que o repositório trata retornos nulos (ex: quando não há doações)
        // para evitar NullPointerException ao converter para o tipo primitivo 'long'.
        Long total = doacaoRepository.totalAlimentosDistribuidos();
        return total != null ? total : 0L;
    }

    @Override
    public Doacao salvar(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    @Override
    public Doacao buscarPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com o ID: " + id));
    }

    @Override
    public List<Doacao> listarTodas() {
        return doacaoRepository.findAll();
    }

    @Override
    public List<Doacao> listarPorDoador(Usuario doador) {
        return doacaoRepository.findByDoador(doador);
    }

    @Override
    public void alterarStatus(Long id, StatusDoacao status) {
        Doacao doacao = buscarPorId(id);
        doacao.setStatus(status);
        doacaoRepository.save(doacao);
    }
}