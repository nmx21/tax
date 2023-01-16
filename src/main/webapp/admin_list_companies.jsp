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
        <title>test</title>
    </head>
<body>
	ADMIN SHOW Companies
<jsp:include page="./inc/header.jsp"/>
<jsp:include page="./inc/admin_menu.jsp"/><br>
    <div>
        <jsp:include page="./inc/admin_company_list_table.jsp"/>
    </div>
    <jsp:include page="./inc/footer.jsp"/>
</body>
</html>