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
    <title>Dashboard</title>
    <c:import url="/head.jsp" />            
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
         	<sidebar:gerente id="1" />
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
                <span class="text-uppercase page-subtitle">Dashboard</span>
                <h3 class="page-title">Atendimentos</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="stats-small stats-small--1 card card-small">
                    <div class="card-body p-0 d-flex">
                        <div class="d-flex flex-column m-auto">
                        <div class="stats-small__data text-center">
                            <span class="stats-small__label text-uppercase">Atendimentos</span>
                            <h6 class="stats-small__value count my-3">${totalAtendimentos}</h6>
                        </div>
                        <div class="stats-small__data">
                            <%-- <span class="stats-small__percentage stats-small__percentage--increase">4.7%</span> --%>
                        </div>
                        </div>
                        <canvas height="120" class="blog-overview-stats-small-1"></canvas>
                    </div>
                    </div>
                </div>
                <div class="col-lg-6 mb-4">
                    <div class="stats-small stats-small--1 card card-small">
                    <div class="card-body p-0 d-flex">
                        <div class="d-flex flex-column m-auto">
                        <div class="stats-small__data text-center">
                            <span class="stats-small__label text-uppercase">Atendimentos Abertos</span>
                            <h6 class="stats-small__value count my-3">${atendimentosAbertos}</h6>
                        </div>
                        <div class="stats-small__data">
                            <span class="stats-small__percentage stats-small__percentage--increase">${porcentagemAbertos}%</span>
                        </div>
                        </div>
                        <canvas height="120" class="blog-overview-stats-small-2"></canvas>
                    </div>
                    </div>
                </div>
            </div>

            <div class="row">
               <div class="col-lg-12">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Atendimentos por Tipo</h6>
                  </div>
                  <div class="card-body p-0 pb-3 text-center">
                    <table class="table mb-0">
                      <thead class="bg-light">
                        <tr>
                          <th scope="col" class="border-bottom-0">Tipo</th>
                          <th scope="col" class="border-bottom-0">Abertos</th>                        
                          <th scope="col" class="border-bottom-0">Total</th>
                        </tr>
                      </thead>                      
                      <tbody> 
                       <c:forEach var="a" items="${atendimentos}">                      
                          <tr>
                            <td>${a.tipo}</td>
                            <td>${a.qtdAtendimentos}</td>
                            <td>${a.qtdAtendimentosAbertos}</td>                  
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script src="https://unpkg.com/shards-ui@latest/dist/js/shards.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Sharrre/2.0.1/jquery.sharrre.min.js"></script>
    <script src="js/shards-dashboards.1.1.0.min.js"></script>
    <script src="js/app-blog-overview.1.1.0.js"></script>  
</html>