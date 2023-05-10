create table ataques(
    id bigint not null auto_increment,
    turno_id bigint not null,
    personagem_id bigint not null,
    pontos int not null,
    forca int not null,
    agilidade int not null,

    primary key(id),
    constraint fk_ataque_turno_id foreign key(turno_id) references turnos(id)
);

