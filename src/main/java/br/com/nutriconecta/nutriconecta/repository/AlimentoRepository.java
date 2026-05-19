package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    Optional<Alimento> findByNomeIgnoreCase(String nome);
}