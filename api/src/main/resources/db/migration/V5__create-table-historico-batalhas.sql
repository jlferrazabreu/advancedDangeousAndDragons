create table historicoBatalhas(
    id bigint not null auto_increment,
    batalha_id bigint not null,
    jogador varchar(100) not null,
    personagem varchar(100) not null,
    acao varchar(100) not null,
    proximaAcao varchar(100),
    numSorteado int,
    dano int,
    pontosVida int,
    data DATETIME,

    primary key(id),
    constraint fk_historicoBatalhas_batalha_id foreign key(batalha_id) references batalhas(id)
);

