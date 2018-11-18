<!DOCTYPE html>
<html lang="pt-br">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <title>Cadastro</title>
    <c:import url="/head.jsp"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
    <div class="container-fluid">
        <div class="row">
            <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0 offset-lg-2 offset-md-3" style="margin-left:10em !important;">
        
            <div class="main-content-container container-fluid px-4">
                    <!-- Page Header -->
                    <div class="page-header row no-gutters py-4">
                    <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                        <span class="text-uppercase page-subtitle">Overview</span>
                        <h3 class="page-title">Cadastro</h3>
                    </div>
                    </div>
                    <!-- End Page Header -->
                    <!-- Content -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card card-small mb-4">
                            <div class="card-header border-bottom">
                                <h6 class="m-0">Preencha o formulário</h6>
                            </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item p-3">
                                        <div class="row">
                                            <div class="col">
                                                <form id="formCliente"  data-type="addUsuario">
                                                    <input type="hidden" name="tipoPerfil" value="C">
                                                    <div class="form-row">
                                                        <div class="form-group col-md-6">
                                                            <label for="nome">Nome</label>
                                                            <input type="text" class="form-control" name="nome" required id="nome" maxlength="50" placeholder="Nome">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label for="cpf">CPF</label>
                                                            <input type="text" class="form-control" id="cpf"  name="cpf" required maxlength="11" placeholder="000.000.000-00">
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="form-group col-md-6">
                                                            <label for="email">Email</label>
                                                            <input type="email" class="form-control" id="email" name="email" required maxlength="50" placeholder="Email">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label for="senha">Senha</label>
                                                            <input type="password" class="form-control" id="senha" name="senha" required maxlength="255" placeholder="Senha">
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="form-group col-md-6">
                                                            <label for="telefone">Telefone</label>
                                                            <input type="text" class="form-control" id="telefone" maxlength="15" required name="telefone" placeholder="(00) 0000-0000">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label for="cep">CEP</label>
                                                            <input type="text" class="form-control" id="cep" name="cep" maxlength="8" required placeholder="00000-000">
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="form-group col-md-6">
                                                            <label for="bairro">Bairro</label>
                                                            <input type="text" class="form-control" name="bairro" maxlength="50" id="bairro" required>
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label for="feInputState">Rua</label>
                                                            <input type="text" class="form-control" id="rua" name="rua" maxlength="100" required>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="form-group col-md-6">
                                                            <label for="complemento">Complemento</label>
                                                            <input type="text" class="form-control" id="complemento" name="complemento"  maxlength="255">
                                                        </div>
                                                        <div class="form-group col-md-6">
                                                            <label for="numero">Numero</label>
                                                            <input type="number" class="form-control" id="numero"  name="numero" maxlength="10" required>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="form-group col-md-4">
                                                            <label for="estado">Estado</label>
                                                            <select id="estado" name="estado" required class="form-control">
                                                                <option selected disabled>Selecione...</option>                                 
                                                                <c:forEach var="estado" items="${estados}">    
                                                                    <option value="${estado.id}">${estado.nome}</option>
                                                                </c:forEach> 

                                                            </select>
                                                        </div>
                                                        <div class="form-group col-md-4">
                                                            <label for="cidade">Cidade</label>
                                                            <select id="cidade" name="cidade" required class="form-control">                            
                                                                <option selected disabled>Selecione um Estado</option>               
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <button type="submit" class="btn btn-accent">Criar</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> 
            </main>   
        </div>    
    </div>
</body>
<c:import url="/scripts.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
<script src="js/formCliente.js"></script>

</html>