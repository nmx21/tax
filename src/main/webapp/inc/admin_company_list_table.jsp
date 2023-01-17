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
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="companyname" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="inn" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="companyaddress" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="do" bundle="${msg}"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${sessionScope.company}">
                <tr>
                    <th scope="row">${row.id}</th>
                    <td>${row.type.type} ${row.name}</td>
                    <td>${row.inn}</td>
                    <td>${row.address}</td>
                    <td><a href="controller?command=showCompanyInfo&id=${row.id}"><fmt:message key="companycard" bundle="${msg}"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<a href ="controller?command=listCompany"><fmt:message key="refresh" bundle="${msg}"/></a><br />
