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

                <c:if test="${not empty message}">
                    <div class="col-xs-12">
                        <div class="alert alert-success alert-dismissible " id="success-alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                                ${message}
                        </div>
                    </div>
                </c:if>

                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Login</h4>
                        </div>
                        <div class="panel-body">

                            <form method="POST"
                                  action="${pageContext.request.contextPath}/login" class="form-horizontal">
                                <div class="form-group">
                                    <label for="username" class="control-label col-md-4">Name</label>
                                    <div class="col-md-8">
                                        <input type="text" id="username" name="username" class="form-control"
                                               placeholder="name"/>
                                        <c:if test="${not empty message}">
                                            <span class="help-block"><em>${messageForName}</em></span>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="control-label col-md-4">Password</label>
                                    <div class="col-md-8">
                                        <input type="password" name="password" id="password" class="form-control"
                                               placeholder="password"/>
                                        <c:if test="${not empty message}">
                                            <span class="help-block"><em>${messageForPassword}</em></span>
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" name="submit" id="submit" value="Sumbit"
                                               class="btn btn-primary"></div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </div>
                            </form>
                        </div>

                        <div>
                            <div class="panel-footer">
                                <div class="text-right">
                                    New User - <a href="${pageContext.request.contextPath}/register">Register Here</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- /.container -->
            </div>


            <%@include file="./shared/footer.jsp" %>
            <!-- /.container -->


            <!-- jQuery -->
            <script src="/resources/js/jquery.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="/resources/js/bootstrap.min.js"></script>

            <!-- self coded script should be the last one-->
            <script src="/resources/js/myapp.js"></script>

        </div>
</body>

</html>


