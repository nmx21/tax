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
	SELECT `company_data`.`id`, `company_data`.`company_type_id` as `type_id`, `company_type`.`type`,`company_data`.`name`, `company_data`.`inn_edrpou`, company_data.address_id FROM `company_data`,`company_type` where `company_data`.`company_type_id` = `company_type`.`id` and `company_data`.`id` in (SELECT `user_has_company_data`.`company_data_id` FROM `user_has_company_data` where `user_has_company_data`.`user_id`=${sessionScope.currentUser.id})   ;
</sql:query>

<sql:query var="result_select_company_type_list" dataSource="jdbc/tax_service">
	select * from company_type;
</sql:query>

<head>
    <jsp:include page="./inc/head.jsp"/>
    <title>Company list</title>
</head>
<body>
    <jsp:include page="./inc/client_menu.jsp"/>
    <jsp:include page="./inc/header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-9">
                <span class="d-block p-2 bg-primary">
                    <details class="text-white">
                        <summary><fmt:message key="companyadd" bundle="${msg}"/></summary>
                        <form  action="controller" method="post">
                            <div class="mb-3">
                                <label for="type" class="form-label"><fmt:message key="type" bundle="${msg}"/></label>
                                <select class="form-control" id="type" name="companyType" required>
                                    <c:forEach var="row" items="${result_select_company_type_list.rows}">
                                        <option value =${row.id}>${row.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label"><fmt:message key="companyname" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="name" name="name" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="inn" class="form-label"><fmt:message key="inn" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="inn" name="inn" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="country" class="form-label"><fmt:message key="companycountry" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="country" name="country" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="region" class="form-label"><fmt:message key="companystate" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="region" name="region" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="city" class="form-label"><fmt:message key="companycity" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="city" name="city" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="street" class="form-label"><fmt:message key="companystreet" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="street" name="street" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="building" class="form-label"><fmt:message key="companybuilding" bundle="${msg}"/></label>
                                <input type="number" class="form-control" id="building" name="building" required minvalue="1" value="1">
                            </div>
                            <div class="mb-3">
                                <label for="buildingLetter" class="form-label"><fmt:message key="letter" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="buildingLetter" name="buildingLetter">
                            </div>
                            <div class="mb-3">
                                <label for="office" class="form-label"><fmt:message key="companyoffice" bundle="${msg}"/></label>
                                <input type="number" class="form-control" id="office" name="office" required minvalue="1" value="1">
                            </div>
                            <div class="mb-3">
                                <label for="officeLetter" class="form-label"><fmt:message key="officeletter" bundle="${msg}"/></label>
                                <input type="text" class="form-control" id="officeLetter" name="officeLetter">
                            </div>
                            <div class="mb-3">
                                <label for="isActual" class="form-label"><fmt:message key="isactualaddress" bundle="${msg}"/></label>
                                <select class="form-control" id="isActual" name="isActual" required>
                                    <option value = "1" selected><fmt:message key="yes" bundle="${msg}"/></option>
                                    <option value = "0"><fmt:message key="no" bundle="${msg}"/></option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="inn" class="form-label"><fmt:message key="adresstype" bundle="${msg}"/></label>
                                <select class="form-control" id="adressType" name="adressType" required>
                                    <option value = "LEGAL" selected><fmt:message key="legaladdress" bundle="${msg}"/></option>
                                    <option value = "REGISTER"><fmt:message key="registeraddress" bundle="${msg}"/></option>
                                </select>
                            </div>
                            <div class="mp-3">
                                <input name="command" value="addCompany" type="hidden">
                            </div>
                            <div class="mp-3">
                                <input name="user" value="" type="hidden">
                            </div>
                            <input value="<fmt:message key="companyadd" bundle="${msg}"/>" type="submit">
                        </form>
                    </details>
                </span>
                <div class="mb-3"><br>
                    <a href ="controller?command=userCompanyList"><fmt:message key="refresh" bundle="${msg}"/></a><br />
                </div>
                <div id="example_wrapper" class="dataTables_wrapper">
                    <table id="table" class="display dataTable" style="width:100%">
                        <thead>
                            <tr>
                                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="type" bundle="${msg}"/></th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="companyname" bundle="${msg}"/></th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="inn" bundle="${msg}"/>/ЄДРПОУ</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="do" bundle="${msg}"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="row" items="${result_select_user_company.rows}">
                                <tr>
                                    <th scope="row">${row.id}</th>
                                        <td>${row.type}</td>
                                        <td>${row.name}</td>
                                        <td>${row.inn_edrpou}</td>
                                        <td><a href="controller?command=userShowCompanyInfo&id=${row.id}"><fmt:message key="companycard" bundle="${msg}"/></a></td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="./inc/footer.jsp"/>
</body>
</html>