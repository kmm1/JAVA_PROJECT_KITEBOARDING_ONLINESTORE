<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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

        <div class="col-md-offset-2 col-md-8">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Product Management</h4>
                </div>
                <div class="panel-body">

                    <!--FORM ELEMENTS -->
                    <!--Using spring library -->
                    <!--multipart/form-data in needed for uploading images -->
                    <sf:form class="form-horizontal" modelAttribute="product"
                             action="${pageContext.request.contextPath}/manage/products"
                             method="POST"
                             enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="control-label col-md-4" for="name">Enter Product Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="name" id="name" placeholder="Product Name"
                                          class="form-control"/>
                                <sf:errors path="name" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="description">Enter Product Description:</label>
                            <div class="col-md-8">
                                <sf:textarea type="text" path="description" id="description"
                                             placeholder="Product Description" rows="4" class="form-control"/>
                                <sf:errors path="description" element="em" cssClass="help-block"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="price">Enter Price in &#36;:</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="price" id="price" placeholder="0.00"
                                          class="form-control"/>
                                <sf:errors path="price" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="amountAvailable">Enter Amount
                                Available:</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="amountAvailable" id="amountAvailable"
                                          placeholder="0" class="form-control"/>
                                <sf:errors cssClass="help-block" path="amountAvailable" element="em"/>
                            </div>
                        </div>

                        <!--File element for image upload -->
                        <div class="form-group">
                            <label class="control-label col-md-4" for="multipartFile">Enter the Image:</label>
                            <div class="col-md-8">
                                <sf:input type="file" path="multipartFile" id="multipartFile" class="form-control"/>
                                <sf:errors path="multipartFile" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4">Choose the activation:</label>
                            <div class="col-md-8">
                                <label class="radio:include">
                                    <sf:radiobutton path="availability" value="1" checked="checked"/>in Stock
                                </label>
                                <label class="radio:include">
                                    <sf:radiobutton path="availability" value="0"/>Out of Stock
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="category">Select Category:</label>
                            <div class="col-md-8">
                                <sf:select class="form-control" id="category" path="category.id"
                                           items="${allCategories}"
                                           itemLabel="enumCategory"
                                           itemValue="id"
                                />
                                <div class="text-right">
                                    <br>
                                    <button type="button" data-toggle="modal" data-target="#myCategoryModal"
                                            class="btn btn-warning btn-sm">
                                        Add Category
                                    </button>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Sumbit"
                                       class="btn btn-primary">
                            </div>
                        </div>


                        <%-- for updating excisting products --%>
                        <sf:hidden path="id"/>
                    </sf:form>


                </div>
            </div>
        </div>


    </div>

    <div class="row">

        <div class="col-xs-12">
            <h3>Available Products</h3>
            <hr>
        </div>

        <div class="col-xs-12">

                <div class="container-fluid">
                    <div class="table-responsive">
                        <!--Products table for admin -->
                        <table id="adminProductsTable" class="table table-striped table-bordered">

                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>&#160;</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Unit Price</th>
                                <th>Amount Available</th>
                                <th>Active</th>
                                <th>Edit</th>
                            </tr>
                            </thead>

                            <tfoot>
                            <tr>
                                <th>Id</th>
                                <th>&#160;</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>&#36; Unit Price</th>
                                <th>Amount Available</th>
                                <th>Active</th>
                                <th>Edit</th>
                            </tr>
                            </tfoot>

                        </table>
                </div>

            </div>

        </div>

    </div>

    <%--is not functional here because of enum Category--%>
    <div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <!--Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">Add new Category</h4>
                </div>
                <!--Modal Body -->
                <div class="modal-body">
                    <form modelAttribute="category" action="${pageContext.request.contextPath}/manage/category"
                          method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label for="enumCategory" class="control-label col-md-4">Category Name</label>
                            <div class="col-md-8">
                                <input type="text" name="enumCategory" id="enumCategory" class="form-control"
                                       style="text-transform:uppercase"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" value="Add category" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                </div>


            </div>
        </div>
    </div>

</div>