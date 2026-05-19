package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.repository.UsuarioRepository;
import br.com.nutriconecta.nutriconecta.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementação da lógica de negócio para usuários
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Conta quantas instituições existem no sistema (usa @Query no repository)
    @Override
    public long countInstituicoes() {
        return usuarioRepository.countInstituicoes();
    }

    // Salva ou atualiza um usuário
    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Busca um usuário pelo ID, lança exceção se não encontrar
    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Busca um usuário pelo e-mail, usado por exemplo no login
    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    // Lista todos os usuários cadastrados
    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Remove um usuário pelo ID
    @Override
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
