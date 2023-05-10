create table turnos(
    id bigint not null auto_increment,
    batalha_id bigint not null,
    personagem_id bigint not null,

    primary key(id),
    constraint fk_turno_batalha_id foreign key(batalha_id) references batalhas(id),
    constraint fk_turno_personagem_id foreign key(personagem_id) references personagens(id)
);

