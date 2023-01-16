<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <title>test</title>
</head>
<body>
    <jsp:include page="./inc/client_menu.jsp"/>
    <jsp:include page="./inc/header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-9">
                <span class="d-block p-2 bg-primary">
                    <details class="text-white">
                        <summary>Додати компанію</summary>
                        <form  action="controller" method="post">
                            <div class="mb-3">
                                <label for="type" class="form-label">Тип компанії</label>
                                <select class="form-control" id="type" name="companyType" required>
                                    <c:forEach var="row" items="${result_select_company_type_list.rows}">
                                        <option value =${row.id}>${row.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label">Назва</label>
                                <input type="text" class="form-control" id="name" name="name" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="inn" class="form-label">ІНН/ЄДРПОУ</label>
                                <input type="text" class="form-control" id="inn" name="inn" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="country" class="form-label">Країна</label>
                                <input type="text" class="form-control" id="country" name="country" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="region" class="form-label">Область</label>
                                <input type="text" class="form-control" id="region" name="region" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="city" class="form-label">Місто</label>
                                <input type="text" class="form-control" id="city" name="city" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="street" class="form-label">Вулиця</label>
                                <input type="text" class="form-control" id="street" name="street" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="building" class="form-label">Будинок</label>
                                <input type="number" class="form-control" id="building" name="building" required minvalue="1" value="1">
                            </div>
                            <div class="mb-3">
                                <label for="buildingLetter" class="form-label">Літера (буд)</label>
                                <input type="text" class="form-control" id="buildingLetter" name="buildingLetter" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="office" class="form-label">Офіс/квартира</label>
                                <input type="number" class="form-control" id="office" name="office" required minvalue="1" value="1">
                            </div>
                            <div class="mb-3">
                                <label for="officeLetter" class="form-label">Літера (квартира)</label>
                                <input type="text" class="form-control" id="officeLetter" name="officeLetter" required minlength="1">
                            </div>
                            <div class="mb-3">
                                <label for="isActual" class="form-label">Актуальність адреси</label>
                                <select class="form-control" id="isActual" name="isActual" required>
                                    <option value = "1" selected>Так</option>
                                    <option value = "0">Ні</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="inn" class="form-label">Тип адреси</label>
                                <select class="form-control" id="adressType" name="adressType" required>
                                    <option value = "LEGAL" selected>Юридична адреса</option>
                                    <option value = "REGISTER">Фізична адреса</option>
                                </select>
                            </div>
                            <div class="mp-3">
                                <input name="command" value="addCompany" type="hidden">
                            </div>
                            <div class="mp-3">
                                <input name="user" value="" type="hidden">
                            </div>
                            <input value="Додати компанію" type="submit">
                        </form>
                    </details>
                </span>
                <div class="mb-3"><br>
                    <a href ="controller?command=userCompanyList">Оновити</a><br />
                </div>
                <div id="example_wrapper" class="dataTables_wrapper">
                    <table id="table" class="display dataTable" style="width:100%">
                        <thead>
                            <tr>
                                <th class="sorting sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" scope="col">#</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Тип</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Назва</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">ІНН/ЄДРПОУ</th>
                                <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дії</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="row" items="${result_select_user_company.rows}">
                                <tr>
                                    <th scope="row">${row.id}</th>
                                        <td>${row.type}</td>
                                        <td>${row.name}</td>
                                        <td>${row.inn_edrpou}</td>
                                        <td><a href="controller?command=userShowCompanyInfo&id=${row.id}">Картка компанії</a></td>
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