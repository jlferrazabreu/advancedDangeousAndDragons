create table batalhas(
    id bigint not null auto_increment,
    usuario_id bigint not null,
    personUsuario_id bigint not null,
    personComputador_id bigint not null,
    dataInicio DATETIME not null,
    dataFinal DATETIME ,
    ativo BOOLEAN not null,
    vencedor varchar(100),

    primary key(id),
    constraint fk_batalhas_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_batalhas_personagem_id foreign key(personComputador_id) references personagens(id)
);

