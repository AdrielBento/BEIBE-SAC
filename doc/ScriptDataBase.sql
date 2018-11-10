create database beibe;
use beibe;

create table tb_estado(

	id  integer primary key auto_increment,
    UF char(2),
    nome varchar(50)
);

create table tb_cidade(
	
    id integer primary key auto_increment,
    nome varchar(100),
    idEstado integer,
    foreign key (idEstado) references tb_estado(id)
);



create table tb_endereco(
	id integer primary key auto_increment,
    rua varchar(100),
    numero varchar(10),
    complemento varchar(255),
    bairro varchar(11),
    cep varchar(11),
    idCidade integer,
    foreign key (idCidade) references tb_cidade(id)
);


create table tb_usuario(

	id integer primary key auto_increment ,
	nome varchar(50),
	cpf varchar(11),
	email varchar(50),
    telefone varchar(15),
    senha varchar(255),
    tipoPerfil char(1) 
  --  idEndereco not null integer,
    -- foreign key (idEndereco) references tb_endereco(id) 
);

insert into tb_usuario(nome,cpf,email,telefone,senha,tipoPerfil) values('Admin','08093140964','admin@gmail.com','4195248371','admin','G');



