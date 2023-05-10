create table danos(
    id bigint not null auto_increment,
    turno_id bigint not null,
    personagem_id bigint not null,
    valorDano int not null,
    qtdDado int not null,
    faceDado int not null,

    primary key(id),
    constraint fk_dano_turno_id foreign key(turno_id) references turnos(id)
);

