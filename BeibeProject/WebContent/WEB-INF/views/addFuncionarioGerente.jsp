<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>
<%@page import="beans.Categoria"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastra Usuario</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
        	<sidebar:gerente id="2" />
        </aside>
        <!-- End Sidebar -->
        <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0 offset-lg-2 offset-md-3">
          <!-- Navbar -->
            <c:import url="/navbar.jsp" /> 
          <!-- END Navbar -->
          <div class="main-content-container container-fluid px-4">
            <!-- Page Header -->
            <div class="page-header row no-gutters py-4">
              <div class="col-12 col-sm-4 text-center text-sm-left mb-0">
                <span class="text-uppercase page-subtitle">Overview</span>
                <h3 class="page-title">Perfil</h3>
              </div>
            </div>
            <!-- End Page Header -->
         
            <!-- Content -->
            <div class="row">
                <div class="col-lg-12">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">     
                    <h6 class="m-0">Cadastra Usuario</h6>            
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                            <form id="formUsuario" data-type="addUsuario">
                                
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="nome">Nome</label>
                                        <input type="text" class="form-control" name="nome" id="nome" maxlength="50" placeholder="Nome" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cpf">CPF</label>
                                        <input type="text" class="form-control"   id="cpf"  name="cpf" maxlength="11" placeholder="000.000.000-00" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control"  id="email" name="email" maxlength="50" placeholder="Email" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="senha">Senha</label>
                                        <input type="password" class="form-control" id="senha" name="senha" maxlength="255" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="telefone">Telefone</label>
                                        <input type="text" class="form-control" id="telefone"maxlength="15" name="telefone" placeholder="(00) 0000-0000" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cep">CEP</label>
                                        <input type="text" class="form-control" id="cep"  name="cep" maxlength="8" placeholder="00000-000" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro</label>
                                        <input type="text" class="form-control" name="bairro" maxlength="50" id="bairro" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="feInputState">Rua</label>
                                        <input type="text" class="form-control" id="rua"  name="rua" maxlength="100" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="complemento">Complemento</label>
                                        <input type="text" class="form-control" id="complemento"  name="complemento" maxlength="255" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="numero">Numero</label>
                                        <input type="number" class="form-control" id="numero"  name="numero" maxlength="10" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="estado">Estado</label>
                                        <select id="estado" name="estado" class="form-control" required>
                                            <option selected disabled>Selecione...</option>                                 
                                            <c:forEach var="estado" items="${estados}">    
                                                <option value="${estado.id}">${estado.nome}</option>
                                            </c:forEach> 

                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="cidade">Cidade</label>
                                        <select id="cidade" name="cidade" class="form-control" required>                            
                                            <option selected disabled>Selecione um Estado</option>     
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="tipoPerfil">Tipo</label>
                                        <select id="tipoPerfil" name="tipoPerfil" class="form-control" required>                            
                                            <option selected disabled>Selecione um Tipo</option>  
                                            <option value="F">Funcionario</option>    
                                            <option value="G">Gerente</option> 
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <button type="submit" class="btn btn-accent">Salvar</button>
                                </div>
                            </form>
                        </div>
                      </div>
                    </li>
                  </ul>
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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>  
<script src="js/formFuncionariGerente.js"></script>  

</html>