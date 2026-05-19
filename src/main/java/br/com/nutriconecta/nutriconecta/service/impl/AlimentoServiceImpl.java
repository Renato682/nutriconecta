package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Alimento;
import br.com.nutriconecta.nutriconecta.repository.AlimentoRepository;
import br.com.nutriconecta.nutriconecta.service.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service indica que esta classe é um componente de serviço do Spring (camada de negócio)
// @RequiredArgsConstructor (Lombok) gera um construtor com todos os atributos final
@Service
@RequiredArgsConstructor
public class AlimentoServiceImpl implements AlimentoService {

    // Injeção de dependência do repositório via construtor gerado pelo Lombok
    private final AlimentoRepository alimentoRepository;

    // Salva ou atualiza um alimento no banco de dados
    @Override
    public Alimento salvar(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    // Retorna todos os alimentos cadastrados
    @Override
    public List<Alimento> listarTodos() {
        return alimentoRepository.findAll();
    }

    // Busca um alimento pelo ID, lança exceção se não encontrar
    @Override
    public Alimento buscarPorId(Long id) {
        return alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));
    }

    // Remove um alimento pelo ID
    @Override
    public void deletar(Long id) {
        alimentoRepository.deleteById(id);
    }
}
