<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:set var="language" value="${not empty param.language ?
  param.language : not empty language ? language :
  pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" var="msg" />
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html lang="${language}">

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
        <p class="text-danger">${sessionScope.message}</p>
        <%  session.removeAttribute( "message");    %>
        <div>
            <div id="example_wrapper" class="dataTables_wrapper">
                <jsp:include page="./inc/admin_reports_table.jsp"/>
            </div>
            <a href ="controller?command=listUsers"><fmt:message key="refresh" bundle="${msg}"/></a><br />
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>