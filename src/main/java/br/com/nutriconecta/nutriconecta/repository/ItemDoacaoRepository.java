package br.com.nutriconecta.nutriconecta.repository;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.ItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemDoacaoRepository extends JpaRepository<ItemDoacao, Long> {
    List<ItemDoacao> findByDoacao(Doacao doacao);
}