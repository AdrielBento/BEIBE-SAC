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
    <title>Atendimentos Abertos</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
        <c:choose>
           <c:when test="${login.tipo == 'F'}">
              <sidebar:funcionario id="1" />
          </c:when>
          <c:otherwise>
              <sidebar:gerente id="4" />
          </c:otherwise>       
        </c:choose>
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
                <h3 class="page-title">Atendimentos Abertos</h3>
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
                          <th scope="col" class="border-bottom-0">Prioridade</th>
                          <th scope="col" class="border-bottom-0"></th>
                        </tr>
                      </thead>
                      <tbody>   
                        <c:set value="" var="atrasado"/>          
                        <c:forEach var="a" items="${atendimentos}">
                            <tr>
                                <td><fmt:formatDate type="both" value="${a.dataHora}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
                                <td>${a.produto.nome}</td>
                                <td>${a.tipo.nome}</td>              
                                <td><a href="#" class="badge badge-pill badge-success">Aberto</a></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${a.late == true}">
                                            <span class="pointer icon-red" data-id="${a.id}">
                                                <i class="fas fa-exclamation-triangle"></i>
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="pointer icon-amarelo" data-id="${a.id}">
                                                <i class="fas fa-exclamation-circle"></i>
                                            </span>
                                        </c:otherwise>
                                    </c:choose>                                   
                                </td>
                        
                                <td>                            
                                  <c:if test="${login.tipo == 'F'}" >
                                        <a href="Funcionario?action=viewAtendimento&id=${a.id}">
                                            <span class="atendimento-view pointer" data-id="${a.id}">
                                                <i class="fas fa-door-open"></i>
                                            </span>
                                        </a>
                                  </c:if>               
                                </td>
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
<script >
        
  <c:if test="${not empty message}">
    new Noty({
        text: "${message}",
        type: "${type}",
        timeout: 3500,
        progressBar: true,
        theme: "metroui",
        animation: {
            open: "animated bounceInRight",
            close: "animated bounceOutRight"
        }
    }).show();
  </c:if>
</script>
</html>