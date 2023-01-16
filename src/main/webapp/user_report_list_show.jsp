<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<jsp:include page="head.jsp"/>
<body>

<sql:query var="result_select_report_status_list" dataSource="jdbc/tax_service">
	SELECT `report_status`.`id`,
        `report_status`.`name`
    FROM `tax_service`.`report_status`;
</sql:query>
<sql:query var="result_select_company_type_list" dataSource="jdbc/tax_service">
    SELECT `report_type`.`id`,
        `report_type`.`company_type_id`,
        `report_type`.`report_type`
    FROM `tax_service`.`report_type`;
</sql:query>

CABINET LIST
    <jsp:include page="./inc/header.jsp"/>
    <div class="container">
          <div class="row">
                <div class="col-2">
                    <jsp:include page="./inc/cabinet_menu.jsp"/>
                </div>
                <div class="col-9">
                <span class="d-block p-2 bg-secondary"><a class="text-white" href = "./add">Add report</a></span>
                    <form>
                        <div class="mb-3">
                            <label for="date_from" class="form-label">Дата з:</label>
                            <input type="date" class="form-control" id="date_from" >
                        </div>
                        <div class="mb-3">
                             <label for="date_for" class="form-label">Дата до:</label>
                             <input type="date" class="form-control" id="date_for" >
                        </div>
                        <div class="mb-3">
                            <label for="type" class="form-label">Тип звіту</label>
                            <select class="form-control" id="type" required>
                                <option value = "0">Усі</option>
                                <c:forEach var="row" items="${result_select_company_type_list.rows}">
                                    <option value =${row.id}>${row.report_type}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Статус</label>
                            <select class="form-control" id="status" required>
                                <option value="0" selected>Усі</option>
                                <c:forEach var="row" items="${result_select_report_status_list.rows}">
                                	<option value =${row.id}>${row.name}</option>
                                </c:forEach>
                            </select>
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