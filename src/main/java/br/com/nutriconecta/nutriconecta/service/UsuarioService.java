package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Usuario;

import java.util.List;

// Serviço responsável pela lógica de negócio dos usuários
// Inclui doadores, instituições e administradores
public interface UsuarioService {

    // Conta quantas instituições estão cadastradas no sistema
    long countInstituicoes();

    // Salva ou atualiza um usuário
    Usuario salvar(Usuario usuario);

    // Busca um usuário pelo ID
    Usuario buscarPorId(Long id);

    // Busca um usuário pelo e-mail (usado, por exemplo, no login)
    Usuario buscarPorEmail(String email);

    // Lista todos os usuários cadastrados
    List<Usuario> listarTodos();

    // Remove um usuário pelo ID
    void deletar(Long id);
}
