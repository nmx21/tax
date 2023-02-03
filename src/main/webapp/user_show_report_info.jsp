<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <title><fmt:message key="reportinfo" bundle="${msg}"/></title>
    </head>
    <body>
        <jsp:include page="./inc/client_menu.jsp"/>
        <jsp:include page="./inc/header.jsp"/>
        <p class="text-danger">${sessionScope.message}</p>
        <%  session.removeAttribute( "message");    %>
        <div class="container">
            <div class="row">
                <div class="col-9">
                    <jsp:include page="./inc/show_report_table.jsp"/>
                    <span class="d-block p-2 bg-primary"><a class="text-white" href = "controller?command=userReportList"><fmt:message key="return" bundle="${msg}"/></a></span>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>