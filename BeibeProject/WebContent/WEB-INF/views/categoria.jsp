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
    <title>Categorias</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
         	<sidebar:funcionario id="3" />
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
                <h3 class="page-title">Categoria</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
              <div class="col-lg-6">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Gerencia Categoria</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form id="addCategoria">
                            <div class="form-row">                       
                            <div class="form-group col-md-12">
                              <div class="input-group mb-1">
                                <input type="text" class="form-control" name="nomeCategoria" required placeholder="Nome Categoria" id="nomeCategoria">
                                <div class="input-group-append">
                                  <button class="btn btn-accent" type="submit" >Salvar</button>
                                </div>
                              </div> 
                              <div class="cancel-update pointer" style="display:none">cancelar atualizacao</div>                          
                            </div>                        
                          </form>
                        </div>
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Categorias</h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0">
                      <thead class="bg-light">
                        <tr>
                          <th scope="col" class="border-bottom-0">#</th>
                          <th scope="col" class="border-bottom-0">Nome</th>                        
                          <th scope="col" class="border-bottom-0">Acao</th>
                        </tr>
                      </thead>
                      <tbody>
             
                       <c:set var = "count" scope = "page" value = "1"/>
                      <c:forEach var="ca" items="${categorias}">
                         <tr>
                          <td>${count}</td>
                          <td id="categoria-nome-${ca.id}">${ca.nome}</td>
                          <td>
                            <span class="categoria-remove" data-id="${ca.id}">
                             <i class="icon-red fas fa-trash-alt"></i>
                            </span>
                            <span class="categoria-update pointer" data-id="${ca.id}">
                              <i class="fas fa-pen"></i>
                            </span>
                          </td>                  
                        </tr>   
                        <c:set var="count" value="${count + 1}" scope="page"/>  
                      </c:forEach>
                               
                      </tbody>
                    </table>
                  </div>
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
<script src="js/cadastroCategoria.js"></script>
</html>