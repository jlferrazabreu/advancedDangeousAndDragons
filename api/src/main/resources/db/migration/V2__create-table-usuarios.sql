create table usuarios(
    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    personagem_id bigint,

    primary key(id),
    constraint usuarios foreign key fk_usuario_personagem (personagem_id) references personagens(id)
);