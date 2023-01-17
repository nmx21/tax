
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ?
  param.language : not empty language ? language :
  pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" var="msg" />
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html>
<html lang="${language}">

<table class="table table-hover">
    <tr>
        <td><fmt:message key="companyname" bundle="${msg}"/></td>
        <td><h6>${sessionScope.report_info.company.type.type} ${sessionScope.report_info.company.name} </h6></td>
    </tr>
    <tr>
        <td><fmt:message key="inn" bundle="${msg}"/></td>
        <td><h6>${sessionScope.report_info.company.inn}</h6></td>
    </tr>
    <tr>
        <td><fmt:message key="financialincome" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.financialIncome}</td>
    </tr>
    <tr>
        <td><fmt:message key="taxamount" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.taxAmount}</td>
    </tr>
    <tr>
        <td><fmt:message key="description" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.description}</td>
    </tr>
    <tr>
        <td><fmt:message key="comment" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.comment}</td>
    </tr>
    <tr>
        <td><fmt:message key="createdate" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.dateCreate}</td>
    </tr>
    <tr>
        <td><fmt:message key="editdate" bundle="${msg}"/></td>
        <td>${sessionScope.report_info.dateLastEdit}</td>
    </tr>
    <tr>
        <td><fmt:message key="reportstatus" bundle="${msg}"/></td>
    <td>${sessionScope.report_info.reportStatus.type}</td>
    </tr>
</table>