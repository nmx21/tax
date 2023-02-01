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
        <title>Index page</title>
    </head>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <form>
            <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
        </form>

        <div class="container"></div>
            <div class="container">
                <div class="row">
                    <div class="col-sm"></div>
                        <div class="col-sm">
                            <p class="text-danger">${sessionScope.message}</p>
                            <form action="controller" method="post">
                            <div class="mp-3">
                                <input name="command" value="login" type="hidden">
                            </div>
                            <div class="mb-3">
                                <label for="login" class="form-label"><fmt:message key="username" bundle="${msg}"/></label>
                                <input type="text" class="form-control" name="login" id="login" minlength="4" maxlength="16" pattern="[\w]+" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label"><fmt:message key="password"  bundle="${msg}"/></label>
                                <input type="password" class="form-control" name="password" id="password" minlength="4" maxlength="32" pattern="[\w]+" required>
                            </div>
                            <input value=<fmt:message key="enter"  bundle="${msg}"/> type="submit">
                        </form>
                        <fmt:message key="or"  bundle="${msg}"/>
                        <br />
                        <span class="d-block p-2 bg-primary">
                            <a class="text-white" href = "controller?command=registrationForm"><fmt:message key="registration"  bundle="${msg}"/></a>
                        </span>
                    </div>
                    <div class="col-sm"></div>
                </div>
            </div>
            <jsp:include page="./inc/footer.jsp"/>
        </div>
    </body>
</html>