package br.com.nutriconecta.nutriconecta.service;

import br.com.nutriconecta.nutriconecta.model.Retirada;
import br.com.nutriconecta.nutriconecta.model.Solicitacao;

// Serviço responsável pela lógica de negócio das retiradas
public interface RetiradaService {

    // Registra uma nova retirada no sistema
    Retirada registrar(Retirada retirada);

    // Busca a retirada associada a uma determinada solicitação
    Retirada buscarPorSolicitacao(Solicitacao solicitacao);

    java.util.List<Retirada> listarTodas();


}
