package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Endereco;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // Busca a lista de endereços vinculados a um usuário específico
    List<Endereco> findByUsuario(Usuario usuario);
}