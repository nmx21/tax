<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
    <c:when test="${sessionScope.isAuth!='true'}">
        <c:redirect url="/"/>
    </c:when>
</c:choose>

<sql:query var="result_select_user_company" dataSource="jdbc/tax_service">
	SELECT `company_data`.`id`, `company_data`.`company_type_id` as `type_id`, `company_type`.`type`,`company_data`.`name`, `company_data`.`inn_edrpou`, company_data.address_id FROM `company_data`,`company_type` where `company_data`.`company_type_id` = `company_type`.`id` and `company_data`.`id` in (SELECT `user_has_company_data`.`company_data_id` FROM `user_has_company_data` where `user_has_company_data`.`user_id`=${sessionScope.user_info.id})   ;
</sql:query>

<sql:query var="result_select_company_type_list" dataSource="jdbc/tax_service">
	select * from company_type;
</sql:query>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>[admin] User info</title>
    </head>
    <body>
        SHOW USER INFO
        <jsp:include page="./inc/header.jsp"/>
        <div class="container">
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
            <div class="row">
                <div class="col-9">
                    <table class="table table-hover">
                        <tr>
                            <td>Назва</td>
                            <td><h6>${sessionScope.user_info.username} </h6></td>
                        </tr>
                        <tr>
                            <td>Імейл</td>
                            <td><h6>${sessionScope.user_info.email}</h6></td>
                        </tr>
                        <tr>
                            <td>Дата реєстрації</td>
                            <td><h6>${sessionScope.user_info.dateRegistry}</h6></td>
                        </tr>
                        <tr>
                            <td>Статус користувача</td>
                            <td><h6>${sessionScope.user_info.roleId}</h6></td>
                        </tr>
                    </table>
                </div>
                <div class="col-9">
                    <br>
                    <h5>Компанії</h5>
                    <br>
                    <div id="example_wrapper" class="dataTables_wrapper">
                        <table id="table" class="display dataTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Тип</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Назва</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">ІНН/ЄДРПОУ</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дії</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="row" items="${result_select_user_company.rows}">
                                    <tr>
                                        <th scope="row">${row.id}</th>
                                        <td>${row.type}</td>
                                        <td>${row.name}</td>
                                        <td>${row.inn_edrpou}</td>
                                        <td><a href="controller?command=showCompanyInfo&id=${row.id}">Карта компанії</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </thead>
                    </table>
                </div>
            </div>
            <br>
            <h5>Звіти</h5>
            <br>
            <jsp:include page="./inc/admin_reports_table.jsp"/>
        </div>
    <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>