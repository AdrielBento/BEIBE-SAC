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
    <title>Home</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
        <%-- <c:choose>
          <c:when test="">
           	<sidebar:cliente id="2" />
          </c:when>
          <c:otherwise>
          </c:otherwise>
          <c:otherwise>
          </c:otherwise>
        </c:choose> --%>
        	<sidebar:cliente id="2" />
        </aside>
        <!-- End Sidebar -->
        <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0 offset-lg-2 offset-md-3">
          <!-- Navbar -->
          <div class="main-navbar sticky-top bg-white">
          
            <nav class="navbar align-items-stretch navbar-light flex-md-nowrap p-0">
              <div action="#" class="main-navbar__search w-100 d-none d-md-flex d-lg-flex">            
              </div>
              <ul class="navbar-nav border-left flex-row ">
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle text-nowrap px-3" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    <%-- <img class="user-avatar rounded-circle mr-2" src="images/avatars/0.jpg" alt="User Avatar"> --%>
                    <span class="d-none d-md-inline-block">${login.nome}</span>
                  </a>
                  <div class="dropdown-menu dropdown-menu-small">              
                    <a class="dropdown-item text-danger" href="#">
                      <i class="material-icons text-danger">&#xE879;</i> Logout </a>
                  </div>
                </li>
              </ul>
              <nav class="nav">
                <a href="#" class="nav-link nav-link-icon toggle-sidebar d-md-inline d-lg-none text-center border-left" data-toggle="collapse" data-target=".header-navbar" aria-expanded="false" aria-controls="header-navbar">
                  <i class="material-icons">&#xE5D2;</i>
                </a>
              </nav>
            </nav>
          </div>
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

                                     <%-- <c:choose>
                                    <c:when test="">
                                        <sidebar:cliente id="2" />
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                    <c:otherwise>
                                    </c:otherwise>
                                    </c:choose> --%>
                                  <c:forEach var="p" items="${produtos}">
                                 	<option value="${p.id}">${p.nome}</option>
                                  </c:forEach>
                                </select>
                             </div>
                            </div>                         
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Descrição</label>
                                <textarea class="form-control" name="descricao" disabled required  rows="5"></textarea>
                              </div>
                            </div> 
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Solução</label>
                                <textarea class="form-control" name="descricao" required   rows="5">${atendimento.descricao}</textarea>                  
                              </div>
                            </div> 
                            <div class="form-row">  
                              <div class="form-group col-md-6">
                              <button class="btn btn-accent mr-1" type="submit" >Salvar</button>      
                              <button class="btn btn-accent mr-1" type="submit" data-id="${atendimento.id}" >Excluir</button>                 

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