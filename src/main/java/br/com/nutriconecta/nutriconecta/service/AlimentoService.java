package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Alimento;

import java.util.List;

// Camada de serviço para a entidade Alimento
// Aqui definimos as operações de negócio relacionadas a alimentos
public interface AlimentoService {

    // Salva ou atualiza um alimento no banco de dados
    Alimento salvar(Alimento alimento);

    // Retorna todos os alimentos cadastrados
    List<Alimento> listarTodos();

    // Busca um alimento pelo seu ID
    Alimento buscarPorId(Long id);

    // Remove um alimento pelo ID
    void deletar(Long id);
}
