<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <a class="nav-link active" aria-current="page" href="client_page.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=userReportList">Звіти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=userCompanyList">Мої Компанії</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=userProfile">Мій профіль</a>
                </li>
            </ul>
            <a class="nav-link" href="controller?command=userProfile">Мій профіль</a>
            <a class="btn btn-outline-success" href="controller?command=logout">Вихід</a>
        </div>
    </div>
</nav>