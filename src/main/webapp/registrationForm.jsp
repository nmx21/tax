<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ?
  param.language : not empty language ? language :
  pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" var="msg" />
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html lang="${language}">

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Registration Form</title>
    </head>
<body>
    <jsp:include page="./inc/header.jsp"/>

    <div class="container"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <p class="text-danger">${sessionScope.message}</p>
                    <form action="controller" method="post">
                        <div class="mp-3">
                            <input name="command" value="registration" type="hidden">
                        </div>
                        <div class="mb-3">
                            <label for="login" class="form-label"><fmt:message key="username" bundle="${msg}"/></label>
                            <input type="text" class="form-control" name="login" id="login" minlength="4" maxlength="10" required>
                        </div>
                        <div class="mb-3">
                                <label for="email" class="form-label"><fmt:message key="email" bundle="${msg}"/></label>
                                <input type="email" class="form-control" name="email" id="email" minlength="4" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label"><fmt:message key="password" bundle="${msg}"/></label>
                            <input type="password" class="form-control" name="password" id="password" minlength="4" maxlength="32" required>
                        </div>
                        <input value=<fmt:message key="registration" bundle="${msg}"/> type="submit">
                    </form>
                    <span class="d-block p-2 bg-primary">
                        <a class="text-white" href = "index.jsp"><fmt:message key="enter" bundle="${msg}"/></a>
                    </span>
                </div>
            </div>
        </div>
    <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>