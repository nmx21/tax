<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
        <title>Report list</title>
    </head>
    <body>
        <jsp:include page="./inc/client_menu.jsp"/>
        <jsp:include page="./inc/header.jsp"/>
        <div class="container">
            <div class="row">
                <p class="text-danger">${sessionScope.message}</p>
                <div class="col-9">
                    <span class="d-block p-1 bg-primary">
                        <h4><fmt:message key="uploadreport" bundle="${msg}"/>:</h4>
                        <form action="update" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" value="" accept=".xml,.json" required/>
                            <input type="submit" value="<fmt:message key="upload" bundle="${msg}"/>" />
                        </form>
                    </span>
                    <br>
                <jsp:include page="./inc/user_reports_table.jsp"/>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>