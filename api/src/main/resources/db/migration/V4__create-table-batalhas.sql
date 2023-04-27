create table batalhas(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    personagem_id bigint not null,
    ativo BOOLEAN not null,
    dataInicio DATETIME not null,
    dataFinal DATETIME ,

    primary key(id),
    constraint fk_batalhas_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_batalhas_personagem_id foreign key(personagem_id) references personagens(id)
);

