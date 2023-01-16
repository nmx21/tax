<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                <div class="col-9">
                    <jsp:include page="./inc/show_report_table.jsp"/>
                    <span class="d-block p-2 bg-primary"><a class="text-white" href = "controller?command=userReportList">Повернутися</a></span>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>