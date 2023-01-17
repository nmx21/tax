<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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

<sql:query var="result_select_company_type_list" dataSource="jdbc/tax_service">
	select * from company_type;
</sql:query>
	<sql:query var="result_select_users_list" dataSource="jdbc/tax_service">
    	SELECT user.id,user.username, user.create_time, role.name FROM user, role where role.id = user.role_id;
</sql:query>

<div id="example_wrapper" class="dataTables_wrapper">
    <table id="table" class="display dataTable" style="width:100%">
        <thead>
            <tr>
                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="login" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="cando" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="createdate" bundle="${msg}"/></th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1"><fmt:message key="do" bundle="${msg}"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${result_select_users_list.rows}">
                <tr>
                    <th scope="row">${row.id}</th>
                    <td>${row.username}</td>
                    <td>${row.name}</td>
                    <td>${row.create_time}</td>
                    <td><a href="controller?command=showUserInfo&id=${row.id}"><fmt:message key="usercard" bundle="${msg}"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<a href ="controller?command=listUsers"><fmt:message key="refresh" bundle="${msg}"/></a><br />
