<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <img src="./images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="" />
        <a class="navbar-brand nav-link text-warning" href="#">Tax Service</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="admin_page.jsp"><fmt:message key="homepage" bundle="${msg}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=listUsers"><fmt:message key="users" bundle="${msg}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=companyList"><fmt:message key="usercompanies" bundle="${msg}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=adminListReports"><fmt:message key="userreports" bundle="${msg}"/></a>
                </li>
            </ul>
            <a class="nav-link" href="controller?command=adminProfile"><fmt:message key="myprofile" bundle="${msg}"/></a>
            <a class="btn btn-outline-success" href="controller?command=logout"><fmt:message key="exit" bundle="${msg}"/></a>
        </div>
    </div>
</nav>