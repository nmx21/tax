<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
    <c:when test="${sessionScope.isAuth!='true'}">
    <c:redirect url="/"/>
    </c:when>
</c:choose>

<sql:query var="result_select_user_of_company" dataSource="jdbc/tax_service">
	select * from `user` where `id` = (SELECT `user_has_company_data`.`user_id` FROM `user_has_company_data`where `user_has_company_data`.`company_data_id`=${sessionScope.company_info.id})   ;
</sql:query>

<sql:query var="result_select_company_report" dataSource="jdbc/tax_service">
    select * from `report` where `report`.`company_data_id` = ${sessionScope.company_info.id}
</sql:query>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>test</title>
    </head>
    <body>
        COMPANY PROFILE
        <jsp:include page="./inc/header.jsp"/>
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
        <div class="container">
            <div class="row">

                <div class="col-9">
                    <table class="table table-hover">
                        <tr>
                            <td>Назва</td>
                            <td><h6>${sessionScope.company_info.type.type} ${sessionScope.company_info.name} </h6></td>
                        </tr>
                        <tr>
                            <td>Код компанії</td>
                            <td><h6>${sessionScope.company_info.inn}</h6></td>
                        </tr>
                        <tr>
                            <td>Адреса</td>
                            <td>????</td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Країна</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.country}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Область</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.region}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Місто</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.city}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Вулиця</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.street}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Будинок</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.building} ${sessionScope.company_info.address.buildingLetter}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Квартира</span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.office} ${sessionScope.company_info.address.officeLetter}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary">Власник компанії:</span></td>
                            <td><h6>&nbsp
                            <c:forEach var="row" items="${result_select_user_of_company.rows}">
                                <a href = "controller?command=showUserInfo&id=${row.id}">${row.username}</a>
                            </c:forEach>
                            </h6></td>
                        </tr>
                    </table>
                    <jsp:include page="./inc/admin_reports_table.jsp"/>
                </div>
            </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>