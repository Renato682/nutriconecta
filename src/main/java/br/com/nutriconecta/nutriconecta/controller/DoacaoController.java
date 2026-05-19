package br.com.nutriconecta.nutriconecta.controller;

import br.com.nutriconecta.nutriconecta.model.Doacao;
import br.com.nutriconecta.nutriconecta.model.enums.StatusDoacao;
import br.com.nutriconecta.nutriconecta.service.DoacaoService;
import br.com.nutriconecta.nutriconecta.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;
    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("doacoes", doacaoService.listarTodas());
        return "doacoes/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("doacao", new Doacao());
        // Passa os usuários para o <select> do HTML. No futuro, você pode filtrar só pelo TipoUsuario.DOADOR
        model.addAttribute("doadores", usuarioService.listarTodos());
        return "doacoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Doacao doacao) {
        doacaoService.salvar(doacao);
        return "redirect:/doacoes/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        model.addAttribute("doacao", doacaoService.buscarPorId(id));
        return "doacoes/detalhes";
    }

    // Endpoint chamado pelo Modal do Bootstrap via form POST
    @PostMapping("/alterar-status")
    public String alterarStatus(@RequestParam Long id, @RequestParam StatusDoacao status) {
        doacaoService.alterarStatus(id, status);
        return "redirect:/doacoes/listar";
    }
}