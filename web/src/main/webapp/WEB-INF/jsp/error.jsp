<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online shopping - ${title}</title>

    <script>
        window.menu = "${title}"
        window.contextRoot = "${pageContext.request.contextPath}"

    </script>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/bootstrap-readable-theme.css" rel="stylesheet"/>
    <link href="/resources/css/dataTables.bootstrap.css" rel="stylesheet"/>

    <link href="/resources/css/myapp.css" rel="stylesheet"/>
    <link href="/resources/images" rel="stylesheet"/>

</head>

<body>

<div class="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Home</a>
            </div>
        </div>
    </nav>

    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">

                    <div class="jumbotron">
                        <h1>${errorTitle}</h1>
                        <hr>

                        <blockquote style="word-wrap:break-word">
                            ${errorDescription}
                        </blockquote>
                    </div>

                </div>
            </div>
        </div>
    </div>

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

    <!-- self coded script -->
    <script src="/resources/js/myapp.js"></script>

</div>
</body>

</html>