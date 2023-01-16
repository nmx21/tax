<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>test</title>
    </head>
    <body>
        ${sessionScope.ex.message}
           <hr>
        ${ex.printStackTrace()}
        <hr>
        ${ex}
        <hr>
        ${ex.getMessage()}
    </body>
</html>