<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>test</title>
    </head>
<body>
    <jsp:include page="./inc/header.jsp"/>

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
                <label for="login" class="form-label">Логін</label>
                <input type="text" class="form-control" name="login" id="login" minlength="4" maxlength="10" required>
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" name="password" id="password" minlength="4" maxlength="10" required>
              </div>
                <input value="Увійти" type="submit">
            </form>

            <span class="d-block p-2 bg-primary"><a class="text-white" href = "controller?command=registrationForm">Реєстрація</a></span>

            </div>

            <div class="col-sm"></div>

          </div>
        </div>
    <jsp:include page="./inc/footer.jsp"/>
</body>
</html>