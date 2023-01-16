<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div id="example_wrapper" class="dataTables_wrapper">
    <table id="table" class="display dataTable" style="width:100%">
        <thead>
            <tr>
                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Компанія</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Тип</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Прибуток</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Податок</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Опис</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дата</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Статус</th>
                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Додатково</th>
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
                    <td><a href="controller?command=userReportDetails&id=${report.id}">Більш детально</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
