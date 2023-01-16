<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:choose>
<c:when test="${sessionScope.isAuth!='true'}">
    <c:redirect url="/"/>
    </c:when>
</c:choose>

<sql:query var="result_select_user_company" dataSource="jdbc/tax_service">
	SELECT *
    FROM `tax_service`.`company_data`
    WHERE `company_data`.`id` in (select `user_has_company_data`.`company_data_id` from `user_has_company_data` where `user_id`=${sessionScope.currentUser.id} );
</sql:query>

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
                <p class="text-danger">${sessionScope.message}</p>
                <div class="col-9">
                    <span class="d-block p-1 bg-primary">
                        <h4>Завантаження звіту:</h4>
                        <form action="update" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" value="" accept=".xml,.json" />
                            <input type="submit" value="Завантажити" />
                        </form>
                    </span>
                    <br>
                    <!--
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
-->
                <jsp:include page="./inc/user_reports_table.jsp"/>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>