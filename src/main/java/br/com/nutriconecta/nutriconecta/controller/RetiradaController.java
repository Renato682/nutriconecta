package br.com.nutriconecta.nutriconecta.controller;

import br.com.nutriconecta.nutriconecta.model.Retirada;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import br.com.nutriconecta.nutriconecta.service.RetiradaService;
import br.com.nutriconecta.nutriconecta.service.SolicitacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/retiradas")
public class RetiradaController {

    private final RetiradaService retiradaService;
    private final SolicitacaoService solicitacaoService;
    private final DoacaoService doacaoService;

    @GetMapping("/registrar/{idSolicitacao}")
    public String registrar(@PathVariable Long idSolicitacao, Model model) {
        // Código muito mais limpo usando o novo buscarPorId!
        Solicitacao solicitacao = solicitacaoService.buscarPorId(idSolicitacao);

        Retirada retirada = new Retirada();
        retirada.setSolicitacao(solicitacao);

        model.addAttribute("retirada", retirada);
        return "retiradas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Retirada retirada) {
        retiradaService.registrar(retirada);
        // Agora, depois de salvar, redireciona para o painel de retiradas!
        return "redirect:/retiradas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        // 1. Alimentos Disponíveis (Doações Abertas)
        model.addAttribute("doacoesDisponiveis", doacaoService.listarTodas().stream()
                .filter(d -> d.getStatus() != null && d.getStatus().name().equals("ABERTA"))
                .collect(Collectors.toList()));

        // 2. Solicitações Aguardando Confirmação (Pendentes)
        model.addAttribute("solicitacoesPendentes", solicitacaoService.listarTodas().stream()
                .filter(s -> s.getStatus() != null && s.getStatus().name().equals("PENDENTE"))
                .collect(Collectors.toList()));

        // 3. Histórico de Retiradas (O que já saiu)
        model.addAttribute("retiradas", retiradaService.listarTodas());

        return "retiradas/lista";
    }
}