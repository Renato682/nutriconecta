package br.com.nutriconecta.nutriconecta.controller;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.ItemDoacao;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import br.com.nutriconecta.nutriconecta.service.ItemDoacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemDoacaoController {

    private final ItemDoacaoService itemDoacaoService;
    private final DoacaoService doacaoService;

    @GetMapping("/doacao/{idDoacao}")
    public String listarPorDoacao(@PathVariable Long idDoacao, Model model) {
        Doacao doacao = doacaoService.buscarPorId(idDoacao);
        model.addAttribute("doacao", doacao);
        model.addAttribute("itens", itemDoacaoService.listarPorDoacao(doacao));
        return "itens/lista";
    }

    @GetMapping("/novo/{idDoacao}")
    public String novo(@PathVariable Long idDoacao, Model model) {
        model.addAttribute("doacao", doacaoService.buscarPorId(idDoacao));
        return "itens/form";
    }

    // Rota salvar atualizada para suportar Edição e Criação
    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idDoacao,
                         @RequestParam(required = false) Long idItem, // Captura o ID do item se for edição
                         @RequestParam String nomeAlimento,
                         @RequestParam String categoriaAlimento,
                         @RequestParam String unidadeAlimento,
                         @RequestParam Double quantidade,
                         @RequestParam @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd") java.time.LocalDate validade) {

        if (idItem != null) {
            // LÓGICA DE EDIÇÃO: Carrega o registro existente e altera os valores
            ItemDoacao itemExistente = itemDoacaoService.buscarPorId(idItem);
            itemExistente.setQuantidade(quantidade);
            itemExistente.setValidade(validade);

            // Atualiza também o objeto Alimento vinculado a ele
            br.com.nutriconecta.nutriconecta.model.Alimento alimento = itemExistente.getAlimento();
            alimento.setNome(nomeAlimento);
            alimento.setCategoria(categoriaAlimento);
            alimento.setUnidadeMedida(unidadeAlimento);

            itemDoacaoService.salvar(itemExistente);
        } else {
            // LÓGICA DE CRIAÇÃO: Usa a nossa inteligência padrão de fluxo novo
            itemDoacaoService.adicionarItem(idDoacao, nomeAlimento, categoriaAlimento, unidadeAlimento, quantidade, validade);
        }

        return "redirect:/itens/doacao/" + idDoacao;
    }

    @GetMapping("/deletar/{idItem}/{idDoacao}")
    public String deletar(@PathVariable Long idItem, @PathVariable Long idDoacao) {
        itemDoacaoService.deletar(idItem);
        return "redirect:/itens/doacao/" + idDoacao;
    }

    // Rota para abrir a tela de edição preenchida
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ItemDoacao item = itemDoacaoService.buscarPorId(id);
        model.addAttribute("item", item);
        model.addAttribute("doacao", item.getDoacao());
        return "itens/form";
    }



}