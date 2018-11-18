 <%@ attribute name="id" required="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main-navbar">
    <nav class="navbar align-items-stretch navbar-light bg-white flex-md-nowrap border-bottom p-0">
      <a class="navbar-brand w-100 mr-0" href="Funcionario" style="line-height: 25px;">
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
              <a class="nav-link active" href="Funcionario?action=getAtendimentosAbertos">
               <i class="fas fa-door-open fa-lg"></i>
                <span>Atendimentos Abertos</span>
              </a>
          </c:when>
          <c:otherwise>
              <a class="nav-link" href="Funcionario?action=getAtendimentosAbertos">
               <i class="fas fa-door-open fa-lg"></i>
                <span>Atendimentos Abertos</span>
              </a>
          </c:otherwise>
        </c:choose>      
      </li>
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 2}">
            <a class="nav-link active " href="Funcionario?action=getAtendimentos">
             <i class="fas fa-headset"></i> 
              <span>Atendimentos</span>
            </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link" href="Funcionario?action=getAtendimentos">
             <i class="fas fa-headset"></i> 
              <span>Atendimentos</span>
            </a>
          </c:otherwise>
        </c:choose>
      
      </li> 
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 3}">
            <a class="nav-link active " href="Categoria?action=getCategorias">
                <i class="fas fa-tags"></i> 
              <span>Categorias</span>
            </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link " href="Categoria?action=getCategorias">
                <i class="fas fa-tags"></i> 
              <span>Categorias</span>
            </a>
          </c:otherwise>
        </c:choose>     
      </li>  
      <li class="nav-item">
        <c:choose>
          <c:when test="${id == 4}">
            <a class="nav-link active " href="Produto?action=getProdutos">
                <i class="fas fa-boxes"></i> 
              <span>Produtos</span>
            </a>
          </c:when>
          <c:otherwise>
            <a class="nav-link" href="Produto?action=getProdutos">
                <i class="fas fa-boxes"></i> 
              <span>Produtos</span>
            </a>
          </c:otherwise>
        </c:choose>     
      </li>             
    </ul>
  </div>