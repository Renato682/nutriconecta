package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.ItemDoacao;

import java.time.LocalDate;
import java.util.List;

public interface ItemDoacaoService {
    ItemDoacao salvar(ItemDoacao item);
    List<ItemDoacao> listarPorDoacao(Doacao doacao);

    // NOVO: Método inteligente para processar o formulário da tela
    void adicionarItem(Long idDoacao, String nomeAlimento, String categoria, String unidade, Double quantidade, LocalDate validade);

    // NOVO: Para poder apagar um item que foi inserido errado
    void deletar(Long id);

    ItemDoacao buscarPorId(Long id);
}