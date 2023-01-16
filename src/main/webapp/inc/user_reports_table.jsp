<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

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
                                                  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Логін</th>
                                                  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Можливості</th>
                                                  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дата створення</th>
                                                  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">Дії</th>
                                                  </tr>
                                              </thead>
                                              <tbody>
                                                <c:forEach var="row" items="${result_select_users_list.rows}">
                                                    <tr>
                                                        <th scope="row">${row.id}</th>
                                                            <td>${row.username}</td>
                                                            <td>${row.name}</td>
                                                            <td>${row.create_time}</td>
                                                            <td><a href="controller?command=showUserInfo&id=${row.id}">Картка користувача</a></td>
                                                        </tr>
                                                </c:forEach>
                                                </tbody>
                                            </thead>
                                        </table>
                                        </div>
                                        <a href ="controller?command=listUsers">Оновити</a><br />
                                    </div>