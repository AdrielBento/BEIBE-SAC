 <%@ attribute name="id" required="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main-navbar">
    <nav class="navbar align-items-stretch navbar-light bg-white flex-md-nowrap border-bottom p-0">
      <a class="navbar-brand w-100 mr-0" href="Gerente" style="line-height: 25px;">
        <div class="d-table m-auto">
          <img id="main-logo" class="d-inline-block align-top mr-1" style="width: 60px;" src="img/Beibe.svg" alt="Shards Dashboard">
        </div>
      </a>
      <a class="toggle-sidebar d-sm-inline d-md-none d-lg-none">
        <i class="material-icons">&#xE5C4;</i>
      </a>
    </nav>
  </div>
  <div class="nav-wrapper">
    <ul class="nav flex-column">
       <li class="nav-item">
        <c:choose>
          <c:when test="${id == 1}">
              <a class="nav-link active" href="Gerente?action=getUsuarios">
               <i class="fas fa-chart-pie fa-lg"></i>
                <span>Dashboard</span>
              </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link " href="Gerente?action=getUsuarios">
               <i class="fas fa-chart-pie fa-lg"></i>
                <span>Dashboard</span>
              </a>
          </c:otherwise>
        </c:choose>      
      </li>
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 2}">
              <a class="nav-link active" href="Gerente?action=listFuncionarioGerente">
               <i class="fas fa-users fa-lg"></i>
                <span>Usuarios</span>
              </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link " href="Gerente?action=listFuncionarioGerente">
               <i class="fas fa-users fa-lg"></i>
                <span>Usuarios</span>
              </a>
          </c:otherwise>
        </c:choose>      
      </li>
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 3}">
            <a class="nav-link active " href="Gerente?action=getAtendimentos">
             <i class="fas fa-headset"></i> 
              <span>Atendimentos</span>
            </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link" href="Gerente?action=getAtendimentos">
             <i class="fas fa-headset"></i> 
              <span>Atendimentos</span>
            </a>
          </c:otherwise>
        </c:choose>      
      </li> 
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 4}">
            <a class="nav-link active " href="Gerente?action=getAtendimentosAbertos">
                <i class="fas fa-door-open"></i> 
              <span>Atendimentos Abertos</span>
            </a>
          </c:when>
          <c:otherwise>
             <a class="nav-link" href="Gerente?action=getAtendimentosAbertos">
                <i class="fas fa-door-open"></i> 
              <span>Atendimentos Abertos</span>
            </a>
          </c:otherwise>
        </c:choose>     
      </li>

      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 5}">
            <a class="nav-link active " href="Gerente?action=getRelatorios">
                <i class="fas fa-file-contract"></i> 
              <span>Relatorios</span>
            </a>
          </c:when>
          <c:otherwise>
             <a class="nav-link" href="Gerente?action=getRelatorios">
                <i class="fas fa-file-contract"></i> 
              <span>Relatorios</span>
            </a>
          </c:otherwise>
        </c:choose>     
      </li>

               
    </ul>
  </div>