<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div id="example_wrapper" class="dataTables_wrapper">
    <table id="table" class="display dataTable" style="width:100%">
        <thead>
            <tr>
                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Назва</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">ІНН</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Адреса</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дії</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${sessionScope.company}">
                <tr>
                    <th scope="row">${row.id}</th>
                    <td>${row.type.type} ${row.name}</td>
                    <td>${row.inn}</td>
                    <td>${row.address}</td>
                    <td><a href="controller?command=showCompanyInfo&id=${row.id}">Картка компанії</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<a href ="controller?command=listCompany">Оновити</a><br />
