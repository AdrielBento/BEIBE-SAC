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
    <title>Resolve Atendimento</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">  
        	<sidebar:funcionario id="2" />
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
                <h3 class="page-title">Atendimentos</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <fmt:formatDate value="${atendimento.dataHora}" var="fmtData" pattern="dd/MM/yyyy HH:mm:ss" />
            <!-- Content -->
            <div class="row">
                <div class="col-lg-12">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0"><span class="badge badge-pill badge-success">Aberto</span></h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form id="addAtendimento" method="POST" action="Funcionario">
                            <input type="hidden" name="id" value="${atendimento.id}"> 
                            <input type="hidden" name="action" value="resolveAtendimento"> 
                            <div class="form-row">                                      
                              
                              <div class="form-group col-md-4">
                                <label for="tipo">Data</label>                                
                                <input type="text" class="form-control" disabled value="${fmtData}">                              
                             </div>

                              <div class="form-group col-md-4">
                                <label for="tipo">Tipo</label>
                                <select  name="tipo" id="tipo"   disabled class="form-control">    
                                  <option value="1">Reclamação</option>
                                  <option value="2">Crítica</option>
                                  <option value="3">Sugetão</option>
                                  <option value="4">Problema</option>
                                </select>                                
                              </div>

                              <div class="form-group col-md-4">
                                <label for="produto">Produtos</label>
                                <select  name="produto" id="produto"   disabled class="form-control">
                                  <c:forEach var="p" items="${produtos}">
                                   	<option value="${p.id}">${p.nome}</option>
                                  </c:forEach>
                                </select>
                              </div>
                            </div>                         
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Descrição</label>
                                <textarea class="form-control" name="descricao" disabled   rows="5">${atendimento.descricao}</textarea>
                              </div>
                            </div> 
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Solução</label>
                                <textarea class="form-control" name="solucao"  required rows="5">${atendimento.solucao}</textarea>                  
                              </div>
                            </div> 
                            <div class="form-row">  
                              <div class="form-group col-md-6">
                              <a class="btn btn-white mr-1" href="Funcionario" >Voltar</a>      
                              <button class="btn btn-outline-primary" data-id="${atendimento.id}" type="submit">Resolver</button>
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
<script>
  $(`#tipo option[value=${atendimento.tipo.id}]`).prop('selected', true);
  $(`#produto option[value=${atendimento.produto.id}]`).prop('selected', true);
</script>
</html>