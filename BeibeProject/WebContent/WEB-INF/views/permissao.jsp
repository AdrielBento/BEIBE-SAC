<!DOCTYPE html>
<html class="no-js h-100" lang="pt-br">
<%@page import="beans.Login"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/sideBar" prefix="sidebar" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sem Permissao</title>
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
          <c:when test="${login.tipo == 'C'}">
              <sidebar:cliente id="0" />
          </c:when>
          <c:when test="${login.tipo == 'F'}">
              <sidebar:funcionario id="0" />
          </c:when>
          <c:otherwise>
              <sidebar:gerente id="0" />
          </c:otherwise>       
        </c:choose>
        
    
        </aside>
        <!-- End Sidebar -->
        <main class="main-content col-lg-10 col-md-9 col-sm-12 p-0 offset-lg-2 offset-md-3">
          <!-- Navbar -->
           <c:import url="/navbar.jsp" /> 
          <!-- END Navbar -->
          <div class="error">
            <div class="error__content">
              <h2>⛔👮</h2>
              <h3>Você não tem permissão!</h3>
              <p>Por favor volte a pagina inicial.</p>
              <c:choose>
                <c:when test="${login.tipo == 'C'}">
                    <a href="Cliente" class="btn btn-accent btn-pill">← Go Back</a>
                </c:when>
                <c:when test="${login.tipo == 'F'}">
                    <a href="Funcionario" class="btn btn-accent btn-pill">← Go Back</a>
                </c:when>
                <c:otherwise>
                    <a href="G" class="btn btn-accent btn-pill">← Go Back</a>
                </c:otherwise>       
              </c:choose>    
            </div> 
          </div>
        </main>
      </div>
    </div>
</body>
<c:import url="/scripts.jsp" />
 
</html>