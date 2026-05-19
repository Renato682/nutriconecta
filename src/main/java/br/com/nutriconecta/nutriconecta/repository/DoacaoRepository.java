package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findByDoador(Usuario doador);

    List<Doacao> findByStatus(StatusDoacao status);

    @Query("SELECT COALESCE(SUM(d.quantidade), 0) FROM Doacao d")
    long totalAlimentosDistribuidos();
}
