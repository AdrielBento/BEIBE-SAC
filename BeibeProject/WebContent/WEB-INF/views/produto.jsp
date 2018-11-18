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
    <title>Produto</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
           	<sidebar:funcionario id="4" />
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
                                <input type="text" class="form-control" id="nome" required name="nome" placeholder="nome">
                              </div>
                              <div class="form-group col-md-6">
                                <label for="categoria">Categoria</label>
                                <select  name="categoria" id="categoria" required class="form-control">
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
                                  <input type="text" class="form-control" name="peso" required id="peso" placeholder="Peso">
                               </div>
                            </div>
                            <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="descricao">Descricao</label>
                                <textarea class="form-control" name="descricao" id="descricao"  required rows="5">Descricao do produto</textarea>
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