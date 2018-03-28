<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--its neaded for spring security. without it ajax (our deactivation item does not work)--%>
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">

    <title>Online shopping - ${title}</title>

    <script>
        window.menu = "${title}"
        window.contextRoot = "${pageContext.request.contextPath}"

    </script>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-readable-theme.css" rel="stylesheet"/>
    <link href="/resources/css/dataTables.bootstrap.css" rel="stylesheet"/>
    <link href="/resources/css/myapp.css" rel="stylesheet"/>

</head>

<body>

<div class="wrapper">

    <!-- Navigation -->
    <%@ include file="./shared/navbar2.jsp" %>


    <!-- Page Content -->
    <div class="content">
        <div class="container">

            <div class="row">
                <!--Column to display personal Details -->
                <div class="col-sm-offset-4 col-sm-4">
                    <div class="text-center">
                        <h1>Welcome!</h1>
                        <h3>Onlineshooping.com</h3>
                        <h6>You can use your mail address as username to login!</h6>
                        <div>
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-success">Login
                                Here</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- /.container -->


    <%@include file="./shared/footer.jsp" %>
    <!-- /.container -->


    <!-- jQuery -->
    <script src="/resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/js/bootstrap.min.js"></script>

    <!--DataTablePlagin https://datatables.net/ -->
    <script src="/resources/js/jquery.dataTables.js"></script>

    <!--DataTable Bootstrap script datatables.net -->
    <script src="/resources/js/dataTables.bootstrap.js"></script>

    <!--DataTable Bootstrap script http://bootboxjs.com/ -->
    <script src="/resources/js/bootbox.min.js"></script>


    <!-- self coded script should be the last one-->
    <script src="/resources/js/myapp.js"></script>

</div>
</body>

</html>