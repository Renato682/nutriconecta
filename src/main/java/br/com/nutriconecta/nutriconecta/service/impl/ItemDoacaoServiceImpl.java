package br.com.nutriconecta.nutriconecta.service.impl;

import br.com.nutriconecta.nutriconecta.model.Alimento;
import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.ItemDoacao;
import br.com.nutriconecta.nutriconecta.repository.AlimentoRepository;
import br.com.nutriconecta.nutriconecta.repository.ItemDoacaoRepository;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import br.com.nutriconecta.nutriconecta.service.ItemDoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDoacaoServiceImpl implements ItemDoacaoService {

    private final ItemDoacaoRepository itemDoacaoRepository;
    private final AlimentoRepository alimentoRepository;
    private final DoacaoService doacaoService;

    @Override
    public ItemDoacao salvar(ItemDoacao item) {
        return itemDoacaoRepository.save(item);
    }

    @Override
    public List<ItemDoacao> listarPorDoacao(Doacao doacao) {
        return itemDoacaoRepository.findByDoacao(doacao);
    }

    @Transactional // Se der erro no meio do caminho, ele desfaz tudo no banco
    public void adicionarItem(Long idDoacao, String nomeAlimento, String categoria, String unidade, Double quantidade, LocalDate validade) {
        Doacao doacao = doacaoService.buscarPorId(idDoacao);

        // REGRA DO PROFESSOR: Procura o alimento. Se não achar, cria e salva um novo na hora!
        Alimento alimento = alimentoRepository.findByNomeIgnoreCase(nomeAlimento)
                .orElseGet(() -> {
                    Alimento novoAlimento = new Alimento();
                    novoAlimento.setNome(nomeAlimento);
                    novoAlimento.setCategoria(categoria);
                    novoAlimento.setUnidadeMedida(unidade);
                    return alimentoRepository.save(novoAlimento);
                });

        // Agora cria o Item associando a doação e o alimento
        ItemDoacao item = new ItemDoacao();
        item.setDoacao(doacao);
        item.setAlimento(alimento);
        item.setQuantidade(quantidade);
        item.setValidade(validade);

        itemDoacaoRepository.save(item);
    }

    @Override
    public void deletar(Long id) {
        itemDoacaoRepository.deleteById(id);
    }

    @Override
    public ItemDoacao buscarPorId(Long id) {
        return itemDoacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de doação não encontrado com o ID: " + id));
    }
}