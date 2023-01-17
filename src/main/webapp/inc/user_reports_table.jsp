<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div id="example_wrapper" class="dataTables_wrapper">
    <table id="table" class="display dataTable" style="width:100%">
        <thead>
            <tr>
                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="company" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="companytype" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="financeincome" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="taxamount" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="description" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="date" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="status" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="additionally" bundle="${msg}"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${sessionScope.reports}">
                <tr>
                    <th scope="report">${report.id}</th>
                    <td>${report.company.type.type} ${report.company.name}</td>
                    <td>${report.reportType.type}</td>
                    <td>${report.financialIncome}</td>
                    <td>${report.taxAmount}</td>
                    <td>${report.description}</td>
                    <td>${report.dateCreate}</td>
                    <td><b>${report.reportStatus.type}</b></td>
                    <td><a href="controller?command=userReportDetails&id=${report.id}"><fmt:message key="details" bundle="${msg}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
