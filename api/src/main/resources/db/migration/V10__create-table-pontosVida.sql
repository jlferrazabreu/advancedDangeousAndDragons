create table pontosVida(
    id bigint not null auto_increment,
    batalha_id bigint not null,
    personagem_id bigint not null,
    valorDano int,
    pontosVida int,
    ativo int,

    primary key(id),
    constraint fk_pontosVida_batalha_id foreign key(batalha_id) references batalhas(id)
);

