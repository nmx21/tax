<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${sessionScope.isAuth!='true'}">
    <c:redirect url="/"/>
    </c:when>
</c:choose>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>test</title>
    </head>
<body>
	ADMIN
    <jsp:include page="./inc/header.jsp"/>
    <jsp:include page="./inc/admin_menu.jsp"/>
    <br>
    <div class=" text-center text-success">Ласкаво просимо, пане Адмін!</div>


        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>
