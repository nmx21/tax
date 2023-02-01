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

<html>
    <head>
        <jsp:include page="./inc/head.jsp"/>
        <title>Report info</title>
        <script language="JavaScript">
            function enable_textarea(status){
                document.getElementById("comment").disabled=status;
                if(status){
                    document.getElementById("comment").value=""
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="./inc/header.jsp"/>
        <jsp:include page="./inc/admin_menu.jsp"/>
        <br>
        <div class="container ">
            <h3 class="text-center"><fmt:message key="reportdetail" bundle="${msg}"/></h3>
            <div class="col-10">
                <jsp:include page="./inc/admin_show_report_table.jsp"/>
                <div class="text-center">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal">
                        <fmt:message key="changereportdetail" bundle="${msg}"/>
                    </button>
                </div>
            <!-- Modal -->
                <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <form action="controller" method="post">
                            <div class="mp-3">
                                <input name="command" value="editReportStatus" type="hidden">
                            </div>
                            <div class="mp-3">
                                <input name="reportId" value="<%=request.getParameter("id")%>" type="hidden">
                            </div>
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="ModalLabel"><fmt:message key="report" bundle="${msg}"/></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="operation" id="operationOk" value="accept" checked onclick="enable_textarea(true)">
                                        <label class="form-check-label" for="operationOk">
                                            <fmt:message key="accept" bundle="${msg}"/>
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="operation" id="operationCancel" value="reject" onclick="enable_textarea(false)">
                                        <label class="form-check-label" for="operationCancel">
                                            <fmt:message key="disable" bundle="${msg}"/>
                                        </label>
                                    </div>
                                    <textarea id="comment" disabled name="comment" maxlength="10001"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><fmt:message key="return" bundle="${msg}"/></button>
                                    <button type="submit" class="btn btn-success"><fmt:message key="save" bundle="${msg}"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <div class="text-center">
            <br />
            <span class="d-block p-2 bg-primary "><a class="text-white justify-content-center" href = "controller?command=showCompanyInfo&id=${sessionScope.report_info.company.id}"><fmt:message key="return" bundle="${msg}"/></a></span>
        </div>
        <jsp:include page="./inc/footer.jsp"/>
    </body>
</html>