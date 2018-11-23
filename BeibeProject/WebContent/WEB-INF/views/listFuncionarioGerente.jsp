<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>
<%@page import="beans.Atendimento"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lista de Usuarios</title>
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
                <h3 class="page-title">Usuarios</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
              <div class="col-lg-12">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">
                      <span>Lista de Usuarios</span>
                      <a href="Gerente?action=addFuncionarioGerente" class="mb-2 btn btn-sm btn-outline-success ml-1">
                        <i class="fas fa-user-plus"></i>
                      </a>
                    </h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0">
                      <thead class="bg-light">
                        <tr>
                          <th scope="col" class="border-bottom-0">Nome</th>
                          <th scope="col" class="border-bottom-0">CPF</th>                        
                          <th scope="col" class="border-bottom-0">E-mail</th>
                          <th scope="col" class="border-bottom-0">Tipo</th>
                          <th scope="col" class="border-bottom-0">Acoes</th>
                        </tr>
                      </thead>
                      <tbody>            
                      
                      <c:forEach var="u" items="${usuarios}">                  
                        <tr>
                            <td>${u.nome}</td>
                            <td>${u.cpf}</td>
                            <td>${u.email}</td>      
                                                                              
                            <c:choose>
                            	<c:when test="${u.tipo == 'F'}">                          		
                                <td>                                 
                                    <span class="badge badge-pill badge-info">
                                        Funcionario
                                    </span>                                   
                                </td>
                                </c:when>
                                <c:otherwise>
                           
                                <td> 
                                    <span class="badge badge-pill badge-dark">
                                       Gerente
                                    </span>                    
                                </td>
                                </c:otherwise>
                            </c:choose>   

                            <td>
                                <a href="Gerente?action=editUsuario&id=${u.id}">
                                    <span class="pointer">
                                        <i class="fas fa-user-edit"></i>
                                    </span>
                                </a>                                  
                                <span data-id="${u.id}" class="pointer usuario-remove icon-red">
                                    <i class="fas fa-trash"></i>
                                </span>                                
                                <a href="Gerente?action=viewUsuario&id=${u.id}">
                                    <span class="pointer">
                                        <i class="fas fa-eye"></i>
                                    </span>
                                </a>
                            </td>
                        </tr>
                      </c:forEach>
                               
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div> 
        </main>
      </div>
    </div>
</body>
<c:import url="/scripts.jsp" />
<script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>    
<script src="js/listFuncionarioGerente.js"></script>
</html>