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
                            <h4>Sign up - Shipping Address</h4>
                        </div>
                        <div class="panel-body">

                            <sf:form method="POST" modelAttribute="address"
                                     action="${pageContext.request.contextPath}/register/step2" class="form-horizontal">

                                <div class="form-group">
                                    <label for="recieverName" class="control-label col-md-4">Enter Receiver Name</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="recieverName" id="recieverName" class="form-control"
                                                  placeholder="Reciever Name"/>
                                        <sf:errors path="recieverName" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="recieverLastName" class="control-label col-md-4">Enter Receiver
                                        Lastname</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="recieverLastName" id="recieverLastName"
                                                  class="form-control"
                                                  placeholder="Reciever Name"/>
                                        <sf:errors path="recieverLastName" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="addressLineOne" class="control-label col-md-4">Enter Address Line
                                        One</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="addressLineOne" id="addressLineOne"
                                                  class="form-control"
                                                  placeholder="Address Line One"/>
                                        <sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="addressLineTwo" class="control-label col-md-4">Enter Address Line
                                        Two</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="addressLineTwo" id="addressLineTwo"
                                                  class="form-control"
                                                  placeholder="Address Line Two"/>
                                        <sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="city" class="control-label col-md-4">Enter City</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="city" id="city"
                                                  class="form-control"
                                                  placeholder="city"/>
                                        <sf:errors path="city" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="state" class="control-label col-md-4">Enter State</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="state" id="state"
                                                  class="form-control"
                                                  placeholder="state"/>
                                        <sf:errors path="state" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-4" for="country">Country:</label>
                                    <div class="col-md-8">
                                        <sf:select class="form-control" id="country" path="country"
                                                   items="${allCountries}"
                                        />
                                        <sf:errors path="country" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="zip" class="control-label col-md-4">Zip</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="zip" id="zip"
                                                  class="form-control"
                                                  placeholder="Zip"/>
                                        <sf:errors path="zip" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="telephone" class="control-label col-md-4">Telephone</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="telephone" id="telephone"
                                                  class="form-control"
                                                  placeholder="+375"/>
                                        <sf:errors path="telephone" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="additionalInfo" class="control-label col-md-4">Additional Info</label>
                                    <div class="col-md-8">
                                        <sf:input type="text" path="additionalInfo" id="additionalInfo"
                                                  class="form-control" placeholder="Additional Info"/>
                                        <sf:errors path="additionalInfo" cssClass="help-block" element="em"/>

                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <a href="${pageContext.request.contextPath}/register" type="submit"
                                           class="btn btn-primary">
                                            <span class="glyphicon glyphicon-chevron-left"></span>Previous - Personal
                                        </a>
                                        <button type="submit" class="btn btn-primary">
                                            Next - Confirm<span class="glyphicon glyphicon-chevron-right"></span>
                                        </button>
                                    </div>
                                </div>
                            </sf:form>
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