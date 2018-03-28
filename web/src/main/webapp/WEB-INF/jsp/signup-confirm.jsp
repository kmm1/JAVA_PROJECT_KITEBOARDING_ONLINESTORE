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
                <div class="col-sm-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Personal Details</h4>
                        </div>
                        <div class="panel-body">
                            <div class="text-center">
                                <p>Name: ${user.name}</p>
                                <p>Email: ${user.email}</p>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <!--Anchor to move to the edition of Personal Details -->
                            <sf:form method="GET"
                                     action="${pageContext.request.contextPath}/register">
                                <input type="submit" value="Edit" class="btn btn-primary"/>
                            </sf:form>
                        </div>
                    </div>
                </div>

                <!--Column to display Address Details -->
                <div class="col-sm-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Address</h4>
                        </div>
                        <div class="panel-body">
                            <div class="text-center">
                                <p>${address.recieverName} ${address.recieverLastName}</p>
                                <p>Address ${address.addressLineOne} ${address.addressLineTwo}</p>
                                <p>City: ${address.city}</p>
                                <p>State: ${address.state}</p>
                                <p>Country: ${address.country}</p>
                                <p>Zip: ${address.zip}</p>
                                <p>Telephone: ${address.telephone}</p>
                                <p>Additional info: ${address.additionalInfo}</p>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <sf:form method="POST"
                                     action="${pageContext.request.contextPath}/register">
                                <input type="submit" value="Edit" class="btn btn-primary"/>
                            </sf:form></div>
                    </div>
                </div>

            </div>

            <!--To provide the confirm button after displaying the details -->
            <div class="row">
                <!--Column to display personal Details -->
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="text-center">
                        <sf:form method="POST"
                                 action="${pageContext.request.contextPath}/register/success" class="form-horizontal">
                            <input type="submit" value="Confirm" class="btn btn-primary"/>
                        </sf:form>

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