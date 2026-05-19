package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Endereco;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import java.util.List;

public interface EnderecoService {
    List<Endereco> listarPorUsuario(Usuario usuario);
    Endereco salvar(Endereco endereco);
    Endereco buscarPorId(Long id);
    void deletar(Long id);
}