package br.com.nutriconecta.nutriconecta.controller;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;
import br.com.nutriconecta.nutriconecta.model.enums.StatusSolicitacao;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import br.com.nutriconecta.nutriconecta.service.SolicitacaoService;
import br.com.nutriconecta.nutriconecta.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;
    private final DoacaoService doacaoService;
    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("solicitacoes", solicitacaoService.listarTodas());
        return "solicitacoes/lista";
    }

    // Usado pelos botões verde e vermelho na lista de solicitações
    @GetMapping("/status/{id}/{status}")
    public String alterarStatus(@PathVariable Long id, @PathVariable String status) {
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);

        // Atualiza o status da solicitação
        solicitacao.setStatus(StatusSolicitacao.valueOf(status));

        // A MÁGICA ACONTECE AQUI:
        if (status.equals("APROVADA")) {
            Doacao doacao = solicitacao.getDoacao();
            // Muda a doação para CONCLUIDA (ou RESERVADA) para sair do mercado
            doacao.setStatus(StatusDoacao.CONCLUIDA);
            doacaoService.salvar(doacao); // Salva a doação atualizada no banco

            // Bônus: Rejeita automaticamente todas as outras solicitações PENDENTES para essa mesma doação
            List<Solicitacao> outrasSolicitacoes = solicitacaoService.listarTodas().stream()
                    .filter(s -> s.getDoacao().getId().equals(doacao.getId()) && s.getStatus().name().equals("PENDENTE"))
                    .toList();

            for(Solicitacao s : outrasSolicitacoes) {
                s.setStatus(StatusSolicitacao.REJEITADA);
                solicitacaoService.salvar(s);
            }
        }

        solicitacaoService.salvar(solicitacao);
        return "redirect:/solicitacoes/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("solicitacao", new Solicitacao());

        // AQUI ESTÁ A CORREÇÃO: Filtra para pegar SOMENTE as doações com status ABERTA
        List<Doacao> doacoesAbertas = doacaoService.listarTodas().stream()
                .filter(d -> d.getStatus() != null && d.getStatus().name().equals("ABERTA"))
                .toList();

        model.addAttribute("doacoes", doacoesAbertas);

        // Filtro que já tínhamos para pegar SOMENTE as instituições
        List<Usuario> apenasInstituicoes = usuarioService.listarTodos().stream()
                .filter(u -> u.getTipo() != null && u.getTipo().name().equals("INSTITUICAO"))
                .toList();

        model.addAttribute("instituicoes", apenasInstituicoes);

        return "solicitacoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Solicitacao solicitacao) {
        solicitacaoService.salvar(solicitacao);
        return "redirect:/solicitacoes/listar";
    }
}