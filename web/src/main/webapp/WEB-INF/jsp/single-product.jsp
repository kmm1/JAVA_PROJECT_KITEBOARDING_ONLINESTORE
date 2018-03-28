<div class="container">

    <!-- Breadcrumb-->
    <div class="row">
        <div class="col-xs-12">

            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/show/all/products">All Products</a></li>
                <li class="active">${product.name}</li>

            </ol>

        </div>
    </div>

    <div class="row">

        <!-- Display the product image -->
        <div class=" col-xs-12 col-sm-4">

            <div class="trumbnail">
                <img src="${pageContext.request.contextPath}/resources/images/${product.imageURL}"
                     class="img img-responsive">
            </div>

        </div>

        <!-- Desplay the product description -->
        <div class=" col-xs-12 col-sm-8">

            <h3>${product.name}</h3>
            <hr>
            <p>${product.description}</p>
            <hr>
            <h4>Price: <strong> &#36; ${product.price}</strong></h4>
            <hr>

            <c:choose>
                <c:when test="${product.availability==false}">
                    <h6>Available: <span style="color:red">Out of stock!</span></h6>
                </c:when>
                <c:otherwise>
                    <h6>Available: ${product.availability}</h6>
                </c:otherwise>
            </c:choose>
            <security:authorize access="hasAnyAuthority('USER')">
                <c:choose>
                    <c:when test="${product.availability==false}">
                        <a href="#" class="btn btn-success disabled"><strike>
                            <span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike></a>
                    </c:when>

                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/cart/add/${product.id}/product"
                           class="btn btn-success">
                            <span class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>
                    </c:otherwise>
                </c:choose>
            </security:authorize>

            <security:authorize access="hasAnyAuthority('ADMIN')">
                <a href="${pageContext.request.contextPath}/manage/${product.id}/product"
                   class="btn btn-warning">
                    <span class="glyphicon glyphicon-pencil"></span>Edit</a>
            </security:authorize>


            <a href="${pageContext.request.contextPath}/show/all/products" class="btn btn-success">Back</a>


        </div>
    </div>


</div>
