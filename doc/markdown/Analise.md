# Objetos:

- Cliente:
- Funcionario
- Gerente
- Atendimento
- Tipo Atendimento
- Cidade
- Estado
- Produto
- Categoria

# Telas

#### Clientes:

- Cadastro
- Login
- Todos os atendimentos
- Editar dados
- Atendimento Completo
- Criacao de Atendimento

#### Funcionario

- Listagem de todos os atendimentos em Aberto
- Listagem de todos os atendimentos
- Resolução do atendimento
- Categorias:
  1. Visualizar
  2. Cadastrar
  3. Listagem
- Produto:
  1. Visualizar
  2. Cadastrar
  3. Listagem

#### Gerente

- Listagem de Funcionarios e Gerentes
- Visualizacao e criacao de funcionario

---

##### Relatorios:

- Relatório de Funcionários
- Relatório de Produtos Mais Reclamados
- Relatório de Atendimentos em Aberto Por Data
- Relatório de Reclamações

# Arquitetura

##### Servlets

- Atendimento
- Gerente
- Funcionario
- Cliente
- Relatorios

#### Facades

- Atendimento
- Gerente
- Funcionario
- Cliente
- Relatorios

#### DAO(s)

- Atendimento
- Gerente
- Funcionario
- Cliente
- Produto
- Estado
- Cidade
- Categoria
