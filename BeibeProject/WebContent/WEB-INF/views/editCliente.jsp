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
    <title>Editar Perfil</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
        	<sidebar:cliente id="1" />
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
                    <h6 class="m-0">Gerenciar Perfil</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                            <form id="formCliente" data-type="updateUsuario">

                                <input type="hidden" name="idUsuario" value="${cliente.id}">
                                <input type="hidden" name="idEndereco" value="${cliente.endereco.id}">

                                
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="nome">Nome</label>
                                        <input type="text" class="form-control" name="nome" id="nome" maxlength="50" value="${cliente.nome}" placeholder="Nome" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cpf">CPF</label>
                                        <input type="text" class="form-control" id="cpf" disabled  name="cpf" maxlength="11"  value="${cliente.cpf}" placeholder="000.000.000-00" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" disabled  value="${cliente.email}" id="email" name="email" maxlength="50" placeholder="Email" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="senha">Senha</label>
                                        <input type="password" class="form-control" id="senha" name="senha" maxlength="255" placeholder="...." >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="telefone">Telefone</label>
                                        <input type="text" class="form-control" id="telefone" value="${cliente.telefone}" maxlength="15" name="telefone" placeholder="(00) 0000-0000" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cep">CEP</label>
                                        <input type="text" class="form-control" id="cep" value="${cliente.endereco.cep}" name="cep" maxlength="8" placeholder="00000-000" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro</label>
                                        <input type="text" class="form-control" name="bairro" maxlength="50" value="${cliente.endereco.bairro}" id="bairro" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="feInputState">Rua</label>
                                        <input type="text" class="form-control" id="rua" value="${cliente.endereco.rua}" name="rua" maxlength="100" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="complemento">Complemento</label>
                                        <input type="text" class="form-control" id="complemento" value="${cliente.endereco.complemento}" name="complemento" maxlength="255">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="numero">Numero</label>
                                        <input type="number" class="form-control" id="numero" value="${cliente.endereco.numero}"  name="numero" maxlength="10" required>
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
                                               <c:forEach var="cidade" items="${cidades}">    
                                                <option value="${cidade.id}">${cidade.nome}</option>
                                            </c:forEach> 
               
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
          <%-- <footer class="main-footer d-flex p-2 px-3 bg-white border-top">           
          </footer> --%>
        </main>
      </div>
    </div>
</body>
<c:import url="/scripts.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>    
<script src="js/formCliente.js"></script>
<script>

  $(`#estado option[value=${cliente.endereco.cidade.estado.id}]`).prop('selected', true);
  $(`#cidade option[value=${cliente.endereco.cidade.id}]`).prop('selected', true);

</script>
</html>