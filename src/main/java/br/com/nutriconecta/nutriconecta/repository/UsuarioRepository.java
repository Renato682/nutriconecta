package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// Repositório da entidade Usuario
// Usado para gerenciar doadores, instituições e administradores
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo e-mail
    // Retorna Optional para tratar o caso de não existir usuário com esse e-mail
    Optional<Usuario> findByEmail(String email);

    // Verifica se já existe um usuário cadastrado com o e-mail informado
    boolean existsByEmail(String email);

    // Consulta personalizada usando JPQL:
    // Conta quantos usuários são do tipo INSTITUICAO
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.tipo = 'INSTITUICAO'")
    long countInstituicoes();
}
