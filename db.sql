create database dbnutriconecta;
use dbnutriconecta;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('DOADOR', 'INSTITUICAO', 'ADMIN') NOT NULL,
    telefone VARCHAR(20),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE enderecos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(15),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);


CREATE TABLE alimentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100), -- ex: 'Perecível', 'Não perecível', 'Hortifruti'
    unidade_medida VARCHAR(20) -- ex: 'kg', 'un', 'L'
);

CREATE TABLE doacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_doador INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    status ENUM('ABERTA', 'RESERVADA', 'CONCLUIDA', 'CANCELADA') DEFAULT 'ABERTA',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_expiracao DATE,
    FOREIGN KEY (id_doador) REFERENCES usuarios(id)
);

CREATE TABLE itens_doacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_doacao INT NOT NULL,
    id_alimento INT NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    validade DATE,
    FOREIGN KEY (id_doacao) REFERENCES doacoes(id),
    FOREIGN KEY (id_alimento) REFERENCES alimentos(id)
);

CREATE TABLE solicitacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_doacao INT NOT NULL,
    id_instituicao INT NOT NULL,
    status ENUM('PENDENTE', 'APROVADA', 'REJEITADA', 'CANCELADA') DEFAULT 'PENDENTE',
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_doacao) REFERENCES doacoes(id),
    FOREIGN KEY (id_instituicao) REFERENCES usuarios(id)
);

CREATE TABLE retiradas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitacao INT NOT NULL,
    data_retirada DATETIME NOT NULL,
    responsavel VARCHAR(150),
    observacoes TEXT,
    FOREIGN KEY (id_solicitacao) REFERENCES solicitacoes(id)
);
