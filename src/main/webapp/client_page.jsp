${sessionScope.message}
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
        <jsp:include page="./inc/client_menu.jsp"/>
        <jsp:include page="./inc/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-2">

                </div>
                <div class="col-9">
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>