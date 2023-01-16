<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:choose>
    <c:when test="${sessionScope.isAuth!='true'}">
    <c:redirect url="/"/>
    </c:when>
</c:choose>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Reports list</title>
    </head>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
        <div>
            <div id="example_wrapper" class="dataTables_wrapper">
                <jsp:include page="./inc/admin_reports_table.jsp"/>
            </div>
            <a href ="controller?command=listUsers">Оновити</a><br />
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>