<p class="lead">Shop Name</p>
<div class="list-group">
    <c:forEach items="${allCategories}" var="cat">
        <a href="${pageContext.request.contextPath}/show/category/${cat.id}/products"
           class="list-group-item">${cat.enumCategory}</a>
    </c:forEach>
</div>