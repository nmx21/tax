<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-9">
                    <div  class="col-2">
                        <p><b>Your login: ${sessionScope.currentUser.username}</b></p>
                    </div>
                    <form action="controller" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label"><fmt:message key="youremail" bundle="${msg}"/></label>
                            <input type="email" class="form-control" id="email" name="email" value="${sessionScope.currentUser.email}" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label"><fmt:message key="password" bundle="${msg}"/></label>
                            <input type="password" class="form-control" id="password" name="password"  minlength="4" maxlength="10">
                        </div>
                        <div class="mp-3">
                            <input name="command" value="editProfile" type="hidden">
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="edit" bundle="${msg}"/></button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>