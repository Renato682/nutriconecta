package br.com.nutriconecta.nutriconecta.model.enums;

// Enum que representa os possíveis status de uma doação no sistema
public enum StatusDoacao {

    ABERTA("ABERTA"),
    EM_ANDAMENTO("EM ANDAMENTO"),
    RESERVADA("REVERVADA"),
    CONCLUIDA("CONCLUIDA"),
    CANCELADA("CANCELADA");

    // Descrição amigável para exibir na tela
    private final String descricao;

    // Construtor do enum (privado por padrão)
    StatusDoacao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para acessar a descrição
    public String getDescricao() {
        return descricao;
    }
}
