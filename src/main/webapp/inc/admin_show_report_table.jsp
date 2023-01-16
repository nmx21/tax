
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                    <table class="table table-hover">
                        <tr>
                            <td>Компанія</td>
                            <td><h6>${sessionScope.report_info.company.type.type} ${sessionScope.report_info.company.name} </h6></td>
                        </tr>
                        <tr>
                            <td>Код компанії</td>
                            <td><h6>${sessionScope.report_info.company.inn}</h6></td>
                        </tr>
                        <tr>
                            <td>Прибуток</td>
                            <td>${sessionScope.report_info.financialIncome}</td>
                        </tr>
                        <tr>
                            <td>Податок</td>
                            <td>${sessionScope.report_info.taxAmount}</td>
                        </tr>
                        <tr>
                            <td>Опис</td>
                            <td>${sessionScope.report_info.description}</td>
                        </tr>
                        <tr>
                            <td>Коментар</td>
                            <td>${sessionScope.report_info.comment}</td>
                        </tr>
                        <tr>
                            <td>Дата створення</td>
                            <td>${sessionScope.report_info.dateCreate}</td>
                        </tr>
                        <tr>
                            <td>Дата редагування</td>
                            <td>${sessionScope.report_info.dateLastEdit}</td>
                        </tr>
                        <tr>
                            <td>Статус звіту</td>
                            <td>${sessionScope.report_info.reportStatus.type}</td>
                        </tr>

                    </table>