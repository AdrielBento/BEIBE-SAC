<!DOCTYPE html>
<html lang="pt-br">
  <%-- JSP --%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
  <meta charset="UTF-8">

  <title>Login</title>
  <c:import url="head.jsp"/>
  <link rel="stylesheet" href="styles/login.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
</head>

<body class="h-100">
  <div class="col-sm-12">
    <div class="row">
      <div class="h-100 col-sm-12">
        <div class="row d-flex justify-content-center">
          <%-- <strong>Welcome a Beibe - ${message}</strong> --%>
        </div>
        <!-- main -->
        <main class="col-sm-12 d-flex align-items-center justify-content-center">
          <!-- form -->
          <form action="LoginServlet" id="formIn" method="POST" class="col-sm-4">
            <!-- Email -->
            <div class="input-group mb-3">
              <div class="input-group input-group-lg input-group-seamless">
                <span class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="material-icons">email</i>
                  </span>
                </span>
                <input type="email" class="form-control" name="email" id="inputEmail" required placeholder="Email">
                <div class="invalid-feedback">Email invalido!</div>
              </div>
            </div>
            <!-- Password -->
            <div class="input-group mb-3">
              <div class="input-group input-group-lg input-group-seamless">
                <input type="password" name="senha" class="form-control" id="inputSenha" required placeholder="Password">
                <span class="input-group-append">
                  <span class="input-group-text">
                    <i class="material-icons">lock</i>
                  </span>
                </span>
                <div class="invalid-feedback">Senha invalida!</div>

              </div>
            </div>
            <div class="input-group mb-3">
              <button type="submit" id="loginIn" class="mb-2 btn btn-primary mr-2">Log In</button>
            </div>
            <div class="input-group mb-3">
              <span class="font-weight-light">Não tem uma conta ? <a href="CadastroCliente"><strong>Criar</strong></a></span>
            </div>

          </form>
          <!-- fim form -->
        </main>
      </div>

    </div>
  </div>

  <c:import url="/scripts.jsp"/>
  <script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
  <script>
    
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
</body>

</html>