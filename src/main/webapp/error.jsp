<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Error</title>
    </head>
    <body>
        <hr>
        Error, try again...
        ${sessionScope.ex.message}
           <hr>
        ${ex.printStackTrace()}
        <hr>
        ${ex}
        <hr>
        ${ex.getMessage()}
    </body>
</html>