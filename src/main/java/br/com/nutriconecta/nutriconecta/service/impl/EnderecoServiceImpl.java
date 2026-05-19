package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Endereco;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.repository.EnderecoRepository;
import br.com.nutriconecta.nutriconecta.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    public List<Endereco> listarPorUsuario(Usuario usuario) {
        return enderecoRepository.findByUsuario(usuario);
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
    }

    @Override
    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
}