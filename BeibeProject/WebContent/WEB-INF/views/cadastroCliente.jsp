<!DOCTYPE html>
<html lang="pt-br">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <c:import url="/head.jsp" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>

<body>
    <div class="col-md-12">
        <form id="formCadastroCliente" action="CadastroCliente" method="POST">

            <input type="hidden" name="action" value="new">
        
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" name="nome" id="nome" maxlength="50" placeholder="Nome">
                </div>
                <div class="form-group col-md-6">
                    <label for="cpf">CPF</label>
                    <input type="text" class="form-control" id="cpf"  name="cpf" maxlength="11" placeholder="000.000.000-00">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" maxlength="50" placeholder="Email">
                </div>
                <div class="form-group col-md-6">
                    <label for="senha">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" maxlength="255" placeholder="Senha">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="telefone">Telefone</label>
                    <input type="text" class="form-control" id="telefone" maxlength="15" name="telefone" placeholder="(00) 0000-0000">
                </div>
                <div class="form-group col-md-6">
                    <label for="cep">CEP</label>
                    <input type="text" class="form-control" id="cep" name="cep" maxlength="8" placeholder="00000-000">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="bairro">Bairro</label>
                    <input type="text" class="form-control" name="bairro" maxlength="50" id="bairro">
                </div>
                <div class="form-group col-md-6">
                    <label for="feInputState">Rua</label>
                    <input type="text" class="form-control" id="rua" name="rua" maxlength="100">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="complemento">Complemento</label>
                    <input type="text" class="form-control" id="complemento" name="complemento" maxlength="255">
                </div>
                <div class="form-group col-md-6">
                    <label for="numero">Numero</label>
                    <input type="number" class="form-control" id="numero"  name="numero" maxlength="10">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="estado">Estado</label>
                    <select id="estado" name="estado" class="form-control">
                        <option selected>Selecione...</option>                                 
                        <c:forEach var="estado" items="${estados}">    
                            <option value="${estado.id}">${estado.nome}</option>
                        </c:forEach> 

                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="cidade">Cidade</label>
                    <select id="cidade" name="cidade" class="form-control">                            
                        <option selected>Selecione um Estado</option>               
                    </select>
                </div>
            </div>
            <div class="form-row">
                <button type="submit" class="btn btn-accent">Criar</button>
            </div>
         </form>
    </div>
</body>
<c:import url="/scripts.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  <script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
<script src="js/cadastroCliente.js"></script>

</html>