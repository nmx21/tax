<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${sessionScope.isAuth!='true'}">
    <c:redirect url="/"/>
 ``   </c:when>
</c:choose>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Users list</title>
    </head>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
        <p class="text-danger">${sessionScope.message}</p>
        <%  session.removeAttribute( "message");    %>
        <div>
            <jsp:include page="./inc/admin_user_list_table.jsp"/>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>