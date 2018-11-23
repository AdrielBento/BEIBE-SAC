<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Relatorios</title>
<c:import url="/head.jsp" />           
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
        <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="styles/shards-dashboards.1.1.0.min.css">
</head>
<body class="h-100">
   <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <aside class="main-sidebar col-12 col-md-3 col-lg-2 px-0">
         	   	<sidebar:gerente id="5" />
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
                <h3 class="page-title">Relatorios</h3>
              </div>
            </div>
            <!-- End Page Header -->
            <!-- Content -->
            <div class="row">
              <div class="col-lg-6">
                <div class="card card-small mb-4">
                  <div class="card-header border-bottom">
                    <h6 class="m-0">Relatorio de Atendimento por data</h6>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item p-3">
                      <div class="row">
                        <div class="col">
                          <form action="Relatorio?relatorio=atendimentoData" target="_blank" method="POST" >   
                                <input type="hidden" name="relatorio" value="atendimentoData"> 
                              <div class="form-group col-md-12">
                                <label for="descricao">Descricao</label>
                                <div id="blog-overview-date-range" class="input-daterange input-group input-group-sm my-auto ml-auto mr-auto ml-sm-auto mr-sm-0">
                                    <input type="text" class="input-sm form-control" name="start" required placeholder="Start Date" id="blog-overview-date-range-1">
                                    <input type="text" class="input-sm form-control" name="end" required placeholder="End Date" id="blog-overview-date-range-2">
                                    <span class="input-group-append">
                                        <span class="input-group-text">
                                        <i class="material-icons"></i>
                                        </span>
                                    </span>
                                </div>
                              </div>     
                                <div class="form-row">
                                    <button type="submit" class="btn btn-accent">Salvar</button>
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
                        <h6 class="m-0">Relatorio de Reclamacoes</h6>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item p-3">
                        <div class="row">
                            <div class="col">
                            <form action="Relatorio?relatorio=paramReclamacoes" target="_blank" method="POST" >
                                <input type="hidden" name="relatorio" value="paramReclamacoes" >
                                 <div class="form-group col-md-12">
                                    <select name="status"  class="form-control" required>
                                        <option value="-1" required disabled>Selecione uma opcao</option>            <option value="T">Todas</option>
                                        <option value="A">Abertas</option>
                                        <option value="F">Fechadas</option>  
                                    </select>   
                                 </div>                       
                                <div class="form-row">
                                    <button type="submit" class="btn btn-primary">Visualizar</button>   
                                </div>                    
                        </form>
                            </div>
                        </div>
                        </li>
                    </ul>
                    </div>
                </div>       
            </div>
             <div class="row">
                <div class="col-lg-6">
                    <div class="card card-small mb-4">
                        <div class="card-header border-bottom">
                            <h6 class="m-0">Relatorio de Produtos Reclamados</h6>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item p-3">
                            <div class="row">
                                <div class="col">
                                    <p class="card-text d-inline-block mb-3">Relatório de Produtos Mais Reclamados: (PDF) deve-se apresentar um relatório com os três produtos da empresa que mais tiveram reclamações;
                                    <a class="btn btn-accent" target="_blank" href="Relatorio?relatorio=maisReclamados" >Gerar</a>
                                </div>
                            </div>
                            </li>
                        </ul>
                    </div>
                </div>  
                <div class="col-lg-6">
                    <div class="card card-small mb-4">
                    <div class="card-header border-bottom">
                        <h6 class="m-0">Relatorio de Funcionarios</h6>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item p-3">
                        <div class="row">
                            <div class="col">
                                <p class="card-text">
                                Relatório de Funcionários: (PDF) deve-se apresentar um relatório contendo todos os funcionários e seus dados</p>
                                <div class="row">
                                  <a class="btn btn-accent" target="_blank" href="Relatorio?relatorio=funcionarios" >Gerar</a>
                                </div>
                            </div>
                        </div>
                        </li>
                    </ul>
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
<script src="https://unpkg.com/shards-ui@latest/dist/js/shards.min.js"></script>
<script src="js/app-blog-overview.1.1.0.js"></script>
</html>