<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body>
CABINET COMPANY
    <jsp:include page="./inc/header.jsp"/>
    <div class="container">
          <div class="row">
                <div class="col-2">
                    <jsp:include page="./inc/client_menu.jsp"/>
                </div>

                <div class="col-9">
                    <form>
                        <div class="mb-3">
                            <label for="login" class="form-label">Логін</label>
                            <input type="email" class="form-control" id="login" aria-describedby="emailHelp">
                            <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="password">
                        </div>
                        <button type="submit" class="btn btn-primary">Пошук</button>
                    </form>

                    <table class="table">
                        <thead>
                            <tr>
                              <th scope="col">#</th>
                              <th scope="col">report_type_id</th>
                              <th scope="col">financial_income</th>
                              <th scope="col">tax_amount</th>
                              <th scope="col">date_create</th>
                              <th scope="col">status_id</th>
                              </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td>2</td>
                              <td>3</td>
                              <td>4</td>
                              <td>5</td>
                              <td>6</td>
                            </tr>
                            <tr>
                               <th scope="row">2</th>
                               <td>2</td>
                               <td>3</td>
                               <td>4</td>
                               <td>5</td>
                               <td>6</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>2</td>
                                <td>3</td>
                                <td>4</td>
                                <td>5</td>
                                <td>6</td>
                            </tr>
                          </tbody>
                        </thead>
                    </table>
                </div>
          </div>
    </div>
    <jsp:include page="./inc/footer.jsp"/>
</body>
</html>