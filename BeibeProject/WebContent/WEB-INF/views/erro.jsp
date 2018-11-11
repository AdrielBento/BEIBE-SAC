
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="img/favicon.ico" />
        <c:import url="/head.jsp" />
      </head>
    <body>      
        <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
                <strong>Exception: ${pageContext.exception}</strong><br>
                <strong>Message:</strong> ${pageContext.exception.message}<br>
                <strong>URL:</strong>${pageContext.errorData.requestURI} <br>
                <strong> StackTrace:</strong>
                <p>${pageContext.out.flush()}</p>
                <p>${pageContext.exception.printStackTrace(pageContext.response.writer)}</p>
            </div>
        </div>  
    </body>
</html>