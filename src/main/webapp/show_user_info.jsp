<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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

<sql:query var="result_select_user_company" dataSource="jdbc/tax_service">
	SELECT `company_data`.`id`, `company_data`.`company_type_id` as `type_id`, `company_type`.`type`,`company_data`.`name`, `company_data`.`inn_edrpou`, company_data.address_id FROM `company_data`,`company_type` where `company_data`.`company_type_id` = `company_type`.`id` and `company_data`.`id` in (SELECT `user_has_company_data`.`company_data_id` FROM `user_has_company_data` where `user_has_company_data`.`user_id`=${sessionScope.user_info.id})   ;
</sql:query>

<sql:query var="result_select_company_type_list" dataSource="jdbc/tax_service">
	select * from company_type;
</sql:query>

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>User info</title>
    </head>
    <body>
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
                <jsp:include page="./inc/admin_company_list_table.jsp"/>
            </div>
            <div class="col-9">
                <h5>Звіти</h5>
                <jsp:include page="./inc/admin_reports_table.jsp"/>
            </div>
        </div>
    <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>