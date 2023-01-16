<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>test</title>
    </head>
    <body>
        <form action="controller" method="post">
            <input name="command" value="createUsers" type="hidden">
            <input name="login1"><br>
            <input name="password1"><br>
            <input name="login2"><br>
            <input name="password2"><br>
            <input value="Create" type="submit">
        </form>
    </body>
</html>
