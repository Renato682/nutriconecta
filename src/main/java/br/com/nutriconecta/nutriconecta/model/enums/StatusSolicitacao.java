package br.com.nutriconecta.nutriconecta.model.enums;

// Enum que representa o status de uma solicitação feita por uma instituição
public enum StatusSolicitacao {

    PENDENTE("PENDENTE"),
    APROVADA("APROVADA"),
    REJEITADA("REJEITADA"),
    CANCELADA("CANCELADA");

    // Texto descritivo para exibição
    private final String descricao;

    // Construtor do enum (privado por padrão)
    StatusSolicitacao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para acessar a descrição
    public String getDescricao() {
        return descricao;
    }
}