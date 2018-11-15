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
    bairro varchar(50),
    cep varchar(11),
    idCidade integer,
    foreign key (idCidade) references tb_cidade(id)
);


create table tb_usuario(

	id integer primary key auto_increment ,
	nome varchar(50),
	cpf varchar(11),
	email varchar(50) unique,
    telefone varchar(15),
    senha varchar(255),
    tipoPerfil char(1),
    idEndereco integer not null,
	foreign key (idEndereco) references tb_endereco(id) 
);


create table tb_categoria(
	id integer primary key auto_increment,
    nome varchar(70)
);

create table tb_tipoAtendimento(
	id integer primary key auto_increment,
    nome varchar(255)
);
create table tb_atendimento(
	
    id integer primary key auto_increment,
    dataHora datetime,
    status varchar(1),
    descricao varchar(255),
    solucao varchar(255),
    idTipoAtendimento integer,
    idProduto integer,
    idUsuario integer,    
    foreign key (idTipoAtendimento) references tb_tipoAendimento(id),
    foreign key (idProduto) references tb_produto(id),
    foreign key (idUsuario) references tb_usuario(id)
);

create table tb_produto(
	id integer primary key auto_increment,
    nome varchar(100),
    descricao varchar(255),
    peso varchar(10),
    idCategoria integer,
    foreign key (idCategoria) references tb_categoria(id)
)

insert into tb_usuario(nome,cpf,email,telefone,senha,tipoPerfil) values('Admin','08093140964','admin@gmail.com','4195248371','admin','G');

INSERT INTO beibe.tb_estado (id,nome,UF) 
SELECT id_estado,nome_estado,sigla_estado
FROM `javaweb`.`tb_estado`;
SELECT * FROM beibe.tb_usuario;

INSERT INTO beibe.tb_cidade (id,nome,idEstado) 
SELECT id_cidade,nome_cidade,id_estado
FROM `javaweb`.`tb_cidade`;

SELECT * FROM beibe.tb_usuario;






