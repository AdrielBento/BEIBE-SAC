<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>
<%@page import="beans.Categoria"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Atendimento</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
 			    <sidebar:cliente id="3" />
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
                <h3 class="page-title">Atendimento</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
                <div class="col-lg-12">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Cadastra Atendimento</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form id="addAtendimento">
                            <div class="form-row">                       
                              <div class="form-group col-md-6">
                                <label for="tipo">Tipo</label>
                                <select  name="tipo" id="tipo"  required class="form-control">
                                  <option value="" disabled selected>Selecione...</option>
                                  <option value="1">Reclamação</option>
                                  <option value="2">Crítica</option>
                                  <option value="3">Sugetão</option>
                                  <option value="4">Problema</option>
                                </select>                                
                             </div>

                              <div class="form-group col-md-6">
                                <label for="produto">Produtos</label>
                                <select  name="produto" id="produto" required class="form-control">
                                  <option value="" disabled selected>Selecione...</option>
                                  <c:forEach var="p" items="${produtos}">
                                 	<option value="${p.id}">${p.nome}</option>
                                  </c:forEach>
                                </select>
                             </div>
                            </div>                         
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Descricao</label>
                                <textarea class="form-control" name="descricao" required id="descricao" rows="5"></textarea>
                              </div>
                            </div> 
                            <div class="form-row">  
                              <div class="form-group col-md-6">
                              <button class="btn btn-accent mr-1" type="submit" >Salvar</button>                               
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
<script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>    
<script src="js/cadastroAtendimento.js"></script>
</html>