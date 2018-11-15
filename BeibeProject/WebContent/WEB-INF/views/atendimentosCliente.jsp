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
                    <h6 class="m-0">Lista de Atendimentos</h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0">
                      <thead class="bg-light">
                        <tr>
                          <th scope="col" class="border-bottom-0">Data</th>
                          <th scope="col" class="border-bottom-0">Produto</th>                        
                          <th scope="col" class="border-bottom-0">Tipo</th>
                          <th scope="col" class="border-bottom-0">Status</th>
                          <th scope="col" class="border-bottom-0">Acoes</th>
                        </tr>
                      </thead>
                      <tbody>            
                      
                      <c:forEach var="a" items="${atendimentos}">                  
                        <tr>
                            <td><fmt:formatDate type="both" value="${a.dataHora}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
                            <td>${a.produto.nome}</td>
                            <td>${a.tipo.nome}</td>                            
                            <td>
                            <c:choose>
                            	<c:when test="${a.status == 'N'}">
                          			<a href="#" class="badge badge-pill badge-danger">${a.status}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" class="badge badge-pill badge-success">${a.status}</a>
                                </c:otherwise>
                            </c:choose>
                            </td>	
                          
                            <td>lixo</td>
                        </tr>
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
<script src="js/cadastroAtendimento.js"></script>
</html>