create table historicoBatalhas(
    id bigint not null auto_increment,
    batalha_id bigint not null,
    turno_id bigint,
    personagem varchar(100) not null,
    acao varchar(100) not null,
    pontosInicio bigint,
    pontosAtaque bigint,
    pontosDefesa bigint,
    qtdDano bigint,
    pontosVida bigint,
    descricao varchar(255),
    data datetime,

    primary key(id),
    constraint fk_historicoBatalhas_batalhas_id foreign key(batalha_id) references batalhas(id)
);

