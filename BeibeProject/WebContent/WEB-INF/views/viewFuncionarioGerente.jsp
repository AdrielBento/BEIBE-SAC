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
    <title>Visualiza Usuario</title>
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
                  <c:choose>
                      <c:when test="${usuario.tipo == 'F'}">
                            <h6 class="m-0">Funcionario</h6>
                      </c:when>
                      <c:otherwise>
                          <h6 class="m-0">Gerente</h6>
                      </c:otherwise>
                  </c:choose>
                
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                            <form id="formUsuario" method="POST" data-type="updateUsuario">

                                <%-- <input type="hidden" name="action" value="updateUsuario" > --%>
                                <input type="hidden" name="idUsuario" value="${usuario.id}">
                                <input type="hidden" name="idEndereco" value="${usuario.endereco.id}">

                                
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="nome">Nome</label>
                                        <input type="text" class="form-control" disabled name="nome" id="nome" maxlength="50" value="${usuario.nome}" placeholder="Nome" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cpf">CPF</label>
                                        <input type="text" class="form-control"  disabled id="cpf"  name="cpf" maxlength="11"  value="${usuario.cpf}" placeholder="000.000.000-00" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" disabled  value="${usuario.email}" id="email" name="email" maxlength="50" placeholder="Email" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="senha">Senha</label>
                                        <input type="password" class="form-control" disabled id="senha" name="senha" maxlength="255" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="telefone">Telefone</label>
                                        <input type="text" class="form-control" id="telefone" disabled value="${usuario.telefone}" maxlength="15" name="telefone" placeholder="(00) 0000-0000" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="cep">CEP</label>
                                        <input type="text" class="form-control" id="cep"  disabled value="${usuario.endereco.cep}" name="cep" maxlength="8" placeholder="00000-000" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="bairro">Bairro</label>
                                        <input type="text" class="form-control" disabled name="bairro" maxlength="50" value="${usuario.endereco.bairro}" id="bairro" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="feInputState">Rua</label>
                                        <input type="text" class="form-control" id="rua" disabled value="${usuario.endereco.rua}" name="rua" maxlength="100" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="complemento">Complemento</label>
                                        <input type="text" class="form-control" id="complemento" disabled value="${usuario.endereco.complemento}" name="complemento" maxlength="255" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="numero">Numero</label>
                                        <input type="number" class="form-control" id="numero" disabled value="${usuario.endereco.numero}"  name="numero" maxlength="10" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="estado">Estado</label>
                                        <select id="estado" name="estado" class="form-control" disabled required>
                                            <option selected disabled>Selecione...</option>                                 
                                            <c:forEach var="estado" items="${estados}">    
                                                <option value="${estado.id}">${estado.nome}</option>
                                            </c:forEach> 

                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="cidade">Cidade</label>
                                        <select id="cidade" name="cidade" class="form-control" disabled required>                            
                                            <option selected disabled>Selecione um Estado</option>
                                               <c:forEach var="cidade" items="${cidades}">    
                                                <option value="${cidade.id}">${cidade.nome}</option>
                                            </c:forEach> 
               
                                        </select>
                                    </div>
                                </div>
                                <%-- <div class="form-row">
                                    <button type="submit" class="btn btn-accent">Salvar</button>
                                </div> --%>
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
<script>

  $(`#estado option[value=${usuario.endereco.cidade.estado.id}]`).prop('selected', true);
  $(`#cidade option[value=${usuario.endereco.cidade.id}]`).prop('selected', true);

</script>
</html>