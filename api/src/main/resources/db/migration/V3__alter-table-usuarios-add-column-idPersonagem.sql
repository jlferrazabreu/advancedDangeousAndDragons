alter table usuarios add column personagem_id bigint;
alter table usuarios add foreign key foreignKey_Personagem (personagem_id) references personagens(id);

