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

<sql:query var="result_select_user_of_company" dataSource="jdbc/tax_service">
	select * from `user` where `id` = (SELECT `user_has_company_data`.`user_id` FROM `user_has_company_data`where `user_has_company_data`.`company_data_id`=${sessionScope.company_info.id})   ;
</sql:query>

<sql:query var="result_select_company_report" dataSource="jdbc/tax_service">
    select * from `report` where `report`.`company_data_id` = ${sessionScope.company_info.id}
</sql:query>
<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Company info</title>
    </head>
    <body>
        <jsp:include page="./inc/client_menu.jsp"/>
        <jsp:include page="./inc/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-9">
                    <table class="table table-hover">
                        <tr>
                            <td><fmt:message key="companyname" bundle="${msg}"/></td>
                            <td><h6>${sessionScope.company_info.type.type} ${sessionScope.company_info.name} </h6></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="companycode" bundle="${msg}"/></td>
                            <td><h6>${sessionScope.company_info.inn}</h6></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="address" bundle="${msg}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companycountry" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.country}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companystate" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.region}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companycity" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.city}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companystreet" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.street}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companybuilding" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.building} ${sessionScope.company_info.address.buildingLetter}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companyoffice" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp${sessionScope.company_info.address.office} ${sessionScope.company_info.address.officeLetter}</h6></td>
                        </tr>
                        <tr>
                            <td><span class="badge bg-secondary"><fmt:message key="companyowner" bundle="${msg}"/></span></td>
                            <td><h6>&nbsp
                            <c:forEach var="row" items="${result_select_user_of_company.rows}">
                                <a href = "controller?command=showUserInfo&id=${row.id}">${row.username}</a>
                            </c:forEach>
                            </h6></td>
                        </tr>
                    </table>
                    <div id="example_wrapper" class="dataTables_wrapper">
                        <table id="table" class="display dataTable" style="width:100%">
                            <thead>
                                <tr>
                                    <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="type" bundle="${msg}"/></th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="financialincome" bundle="${msg}"/>financial_income</th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="taxamount" bundle="${msg}"/></th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="description" bundle="${msg}"/></th>
                                    <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="comment" bundle="${msg}"/></th>
                                </tr>
                            </thead>
                        <tbody>
                            <c:forEach var="row" items="${result_select_company_report.rows}">
                                <tr>
                                    <th scope="row">${row.id}1</th>
                                        <td>${row.report_type_id}2</td>
                                        <td>${row.financial_income}3</td>
                                        <td>${row.tax_amount}4</td>
                                        <td>${row.description}5</td>
                                        <td><a href="#">Some do6</a></td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>