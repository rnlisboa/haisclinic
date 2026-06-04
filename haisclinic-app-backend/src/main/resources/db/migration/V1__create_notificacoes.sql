CREATE TABLE IF NOT EXISTS notificacoes (
    id VARCHAR(255) PRIMARY KEY,
    paciente_id VARCHAR(255) NOT NULL,
    prontuario_id VARCHAR(255) NOT NULL,
    evolucao_id VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    lida BOOLEAN NOT NULL,
    criado_em TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_notificacoes_paciente_id ON notificacoes (paciente_id);
CREATE INDEX IF NOT EXISTS idx_notificacoes_prontuario_id ON notificacoes (prontuario_id);
CREATE INDEX IF NOT EXISTS idx_notificacoes_evolucao_id ON notificacoes (evolucao_id);
