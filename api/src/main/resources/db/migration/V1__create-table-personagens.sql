create table personagens(
 id bigint not null auto_increment,
 tipoPersonagem varchar(100) not null,
 nome varchar(100) not null UNIQUE,
 vida int not null,
 forca int not null,
 defesa int not null,
 agilidade int not null,
 quantidadeDeDados int not null,
 facesDoDado int not null,
 ativo BOOLEAN not null,

 primary key(id)
);