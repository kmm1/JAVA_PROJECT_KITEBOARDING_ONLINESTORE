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
    <%@ include file="./shared/navbar.jsp" %>

    <!-- Page Content -->
    <div class="content">

        <!-- HomePage -->
        <c:if test="${userClickHome==true}">
            <%@include file="home.jsp" %>
        </c:if>

        <!-- Load only when user clickes about -->
        <c:if test="${userClickAbout==true}">
            <%@include file="about.jsp" %>
        </c:if>

        <!-- Load only when user clickes contact-->
        <c:if test="${userClickContact==true}">
            <%@include file="contact.jsp" %>
        </c:if>

        <!-- Load only when user clickes products or CategoryProducts-->
        <c:if test="${userClickAllProducts==true or userClickCategoryProducts==true}">
            <%@include file="list-products.jsp" %>
        </c:if>

        <!-- Load only when user clickes userClickShowProduct-->
        <c:if test="${userClickShowProduct==true}">
            <%@include file="single-product.jsp" %>
        </c:if>

        <c:if test="${userClickManageProduct==true}">
            <%@include file="manage-products.jsp" %>
        </c:if>

        <c:if test="${userClickShowCart==true}">
            <%@include file="cart.jsp" %>
        </c:if>


        <!-- /.container -->
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

    <!--DataTable Bootstrap script http://bootboxjs.com/ -->
    <script src="/resources/js/bootbox.min.js"></script>


    <!-- self coded script should be the last one-->
    <script src="/resources/js/myapp.js"></script>

</div>
</body>

</html>