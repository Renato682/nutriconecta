package br.com.nutriconecta.nutriconecta.controller;

import br.com.nutriconecta.nutriconecta.model.Endereco;
import br.com.nutriconecta.nutriconecta.model.Usuario;
import br.com.nutriconecta.nutriconecta.service.EnderecoService;
import br.com.nutriconecta.nutriconecta.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;

    // Rota que lista os endereços do usuário selecionado
    @GetMapping("/usuario/{idUsuario}")
    public String listarPorUsuario(@PathVariable Long idUsuario, Model model) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("enderecos", enderecoService.listarPorUsuario(usuario));
        return "enderecos/lista";
    }

    // Rota que abre o formulário para adicionar novo endereço
    @GetMapping("/novo/{idUsuario}")
    public String novo(@PathVariable Long idUsuario, Model model) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario); // Vincula o endereço ao usuário pai

        model.addAttribute("usuario", usuario);
        model.addAttribute("endereco", endereco);
        return "enderecos/form";
    }

    // Grava o endereço no banco e redireciona de volta para a lista do usuário
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Endereco endereco) {
        enderecoService.salvar(endereco);
        return "redirect:/enderecos/usuario/" + endereco.getUsuario().getId();
    }

    // Rota para carregar os dados no formulário e editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Endereco endereco = enderecoService.buscarPorId(id);

        // Passamos o endereço e também o usuário dono dele para a tela
        model.addAttribute("endereco", endereco);
        model.addAttribute("usuario", endereco.getUsuario());

        return "enderecos/form";
    }

    // Rota para excluir o endereço e voltar para a lista do usuário
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        Endereco endereco = enderecoService.buscarPorId(id);
        Long idUsuario = endereco.getUsuario().getId(); // Salva o ID do dono antes de apagar

        enderecoService.deletar(id);

        return "redirect:/enderecos/usuario/" + idUsuario; // Volta pra lista do dono
    }
}