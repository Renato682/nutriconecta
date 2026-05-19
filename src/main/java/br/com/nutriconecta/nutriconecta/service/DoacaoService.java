package br.com.nutriconecta.nutriconecta.service;
import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;

import java.util.List;

// Serviço responsável pela lógica de negócio das doações
public interface DoacaoService {

    // Conta quantas doações existem no sistema
    long count();

    // Retorna o total de alimentos distribuídos (regra definida na implementação)
    long totalAlimentosDistribuidos();

    // Salva ou atualiza uma doação
    Doacao salvar(Doacao doacao);

    // Busca uma doação pelo ID
    Doacao buscarPorId(Long id);

    // Lista todas as doações cadastradas
    List<Doacao> listarTodas();

    // Lista as doações feitas por um doador específico
    List<Doacao> listarPorDoador(Usuario doador);

    // Altera o status de uma doação (ABERTA, RESERVADA, etc.)
    void alterarStatus(Long id, StatusDoacao status);
}
