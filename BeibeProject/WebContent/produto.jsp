<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>
<%@page import="beans.Produto"%>

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
                <h3 class="page-title">Produto</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
              <div class="col-lg-6">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Gerencia Produto</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form id="addProduto">
                            <div class="form-row">                       
                              <div class="form-group col-md-6">
                                <label for="feFirstName">Nome</label>
                                <input type="text" class="form-control" id="nome" name="nome" placeholder="nome">
                              </div>
                              <div class="form-group col-md-6">
                                <label for="categoria">Categoria</label>
                                <select  name="categoria" id="categoria" class="form-control">
                                  <option selected>Selecione...</option>
                                  <c:forEach var="c" items="${categoria}">
                                 	<option value="${c.id}">${c.nome}</option>
                                  </c:forEach>
                                </select>
                             </div>
                            </div>
                            <div class="form-row"> 
                                <div class="form-group col-md-12">
                                  <label for="peso">Peso</label>
                                  <input type="text" class="form-control" name="peso" id="peso" placeholder="Peso">
                               </div>
                            </div>
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Descricao</label>
                                <textarea class="form-control" name="descricao" id="descricao" rows="5">Descricao do produto</textarea>
                              </div>
                            </div> 
                            <div class="form-row">  
                              <div class="form-group col-md-6">
                              <button class="btn btn-accent mr-1" type="submit" >Salvar</button>   
                              <button class="btn btn-outline-danger" id="cancel-update-produto" type="button" style="display:none">Cancelar</button>     
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
                    <h6 class="m-0">Produtos</h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0">
                      <thead class="bg-light">
                        <tr>
                          <th scope="col" class="border-bottom-0">#</th>
                          <th scope="col" class="border-bottom-0">Nome</th>
                          <th scope="col" class="border-bottom-0">Categoria</th>
                          <th scope="col" class="border-bottom-0">Peso</th>
                          <th scope="col" class="border-bottom-0">Acoes</th>

                        </tr>
                      </thead>
                      <tbody>
             
                      <c:set var = "count" scope = "page" value = "1"/>
                      <c:forEach var="p" items="${produtos}">
                         <tr>
                          <td>${count}</td>
                          <td id="produto-nome-${p.id}">${p.nome}</td>
                          <td id="produto-categoria-${p.id}">${p.categoria.nome}</td>
                          <td id="produto-peso-${p.id}">${p.peso}</td>
                          <td>
                            <span class="produto-remove pointer" data-id="${p.id}">
                             <i class="icon-red fas fa-trash-alt"></i>
                            </span>
                            <span class="produto-update pointer" data-id="${p.id}">
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
<script src="js/cadastroProduto.js"></script>
</html>