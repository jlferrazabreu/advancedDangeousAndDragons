create table iniciativas(
    id bigint not null auto_increment,
    batalha_id bigint not null,
    personagem_id bigint not null,
    personagem varchar(100),
    ativo BOOLEAN not null,
    pontos int,

    primary key(id),
    constraint fk_iniciativa_batalha_id foreign key(batalha_id) references batalhas(id),
    constraint fk_iniciativa_personagem_id foreign key(personagem_id) references personagens(id)
);

