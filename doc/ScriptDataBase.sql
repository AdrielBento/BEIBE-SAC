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

create table tb_produto(
	id integer primary key auto_increment,
    nome varchar(100),
    descricao varchar(255),
    peso varchar(10),
    idCategoria integer,
    foreign key (idCategoria) references tb_categoria(id)
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
    foreign key (idTipoAtendimento) references tb_tipoAtendimento(id),
    foreign key (idProduto) references tb_produto(id),
    foreign key (idUsuario) references tb_usuario(id)
);


insert into tb_usuario(idEndereco,nome,cpf,email,telefone,senha,tipoPerfil) values(4,'Funcionario','24726765060','funcionario@gmail.com','4195248371','857116C22EA4925520D650AEBFC6DE9921BE197DE0FDF4E502FEDEBD14854A44','F');
insert into tb_usuario(idEndereco,nome,cpf,email,telefone,senha,tipoPerfil) values(4,'Gerente','86519456047','gerente@gmail.com','4195248371','98EC4EE87E461AA980D5EF58D68042C4A5B9B1D957BD04EAC40F8B2FC582816A','G');
INSERT INTO beibe.tb_tipoAtendimento(id,nome) SELECT id_tipo_atendimento,nome_tipo_atendimento FROM `javaweb`.`tb_tipo_atendimento`;
INSERT INTO beibe.tb_estado (id,nome,UF) SELECT id_estado,nome_estado,sigla_estado FROM `javaweb`.`tb_estado`;
INSERT INTO beibe.tb_cidade (id,nome,idEstado) SELECT id_cidade,nome_cidade,id_estado FROM `javaweb`.`tb_cidade`;

select COUNT(a.id) from tb_atendimento a;
Select COUNT(id) as quantidade FROM tb_atendimento where status = "A";

SELECT aten.idTipoAtendimento,COUNT(aten.idTipoAtendimento) as quantidade FROM tb_atendimento aten where aten.status = "A" group by aten.idTipoAtendimento;

SELECT t.nome,COUNT(a.idTipoAtendimento) as total FROM tb_atendimento a 
INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento
group by idTipoAtendimento;

select t.nome, (select count(a1.id) from tb_atendimento  a1 where a1.idTipoAtendimento = a.idTipoAtendimento) as atendimentos, 
(select count(a2.id) from tb_atendimento a2 where a2.idTipoAtendimento = a.idTipoAtendimento AND a2.status = 'A') as Aberto 
from tb_atendimento a 
INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento
group by a.idTipoAtendimento;

