<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

CABINET PROFILE
    <jsp:include page="./inc/header.jsp"/>
    <div class="container">
          <div class="row">
                <div class="col-2">
                    <jsp:include page="./inc/client_menu.jsp"/>
                </div>


                <div class="col-9">
                <div  class="col-2">
                                    <p><b>Your login: ${sessionScope.currentUser.username}</b></p>
                                </div>
                    <form action="controller" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Адреса електронної пошти</label>
                            <input type="email" class="form-control" id="email" name="email" value="${sessionScope.currentUser.email}" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="password" name="password"  minlength="4" maxlength="10">
                        </div>
                        <div class="mp-3">
                            <input name="command" value="editProfile" type="hidden">
                        </div>
                        <button type="submit" class="btn btn-primary">Редагувати</button>
                    </form>
                </div>
          </div>
    </div>
    <jsp:include page="./inc/footer.jsp"/>
</body>
</html>