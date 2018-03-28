<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <c:if test="${not empty message}">
        <div class="col-xs-12">
            <div class="alert alert-warning  alert-dismissible " id="success-alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                    ${message}
            </div>
        </div>
    </c:if>


    <c:choose>

        <c:when test="${userDto.numberOfProductsInCart>0}">

            <table id="cart" class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th style="width:50%">Product</th>
                    <th style="width:10%">Price</th>
                    <th style="width:8%">Quantity</th>
                    <th style="width:22%" class="text-center">Subtotal</th>
                    <th style="width:10%"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${infoAboutProductsInCart}" var="product">

                    <tr>
                        <td data-th="Product">
                            <div class="row">
                                <div class="col-sm-2 hidden-xs">
                                    <img src="${pageContext.request.contextPath}/resources/images/${product.key.imageURL}"
                                         alt="${product.key.name}"
                                         class="img-responsive cartImg"/></div>
                                <div class="col-sm-10">
                                    <h4 class="nomargin">${product.key.name}</h4>
                                    <p>${product.key.description}</p>
                                </div>
                            </div>
                        </td>
                        <td data-th="Price">&#36; ${product.key.price}</td>
                        <td data-th="Quantity">
                            <input type="number" id="count_${product.key.id}" min="1"
                                   max="${product.key.amountAvailable}" class="form-control text-center"
                                   value=${product.value}>
                        </td>
                        <td data-th="Subtotal" class="text-center">${product.key.price*product.value}</td>
                        <td class="actions" data-th="">
                            <button type="button" name="refreshCart" value="${product.key.id}"
                                    class="btn btn-info btn-sm"><span class="glyphicon glyphicon-refresh"></span></button>
                            <a href="${pageContext.request.contextPath}/cart/${product.key.id}/delete" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
                <tfoot>
                <tr class="visible-xs">
                    <td class="text-center"><strong>Total ${cartTotal}</strong></td>
                </tr>
                <tr>
                    <td><a href="${pageContext.request.contextPath}/show/all/products" class="btn btn-warning"><span
                            class="glyphicon glyphicon-chevron-left"></span>
                        Continue
                        Shopping</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                    <td class="hidden-xs text-center"><strong>Total ${cartTotal}</strong></td>
                    <td><a href="${pageContext.request.contextPath}/checkout" class="btn btn-success btn-block">Checkout
                        <span
                                class="glyphicon glyphicon-chevron-right"></span></a></td>
                </tr>
                </tfoot>
            </table>
        </c:when>


        <c:otherwise>
            <div class="jumbotron">
                <div class="text-center">
                    <h1>Your cart is empty</h1>
                </div>
            </div>
        </c:otherwise>
    </c:choose>


</div>