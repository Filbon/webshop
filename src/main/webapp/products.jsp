<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Produkter</title>
</head>
<body>
<h1>Produkter</h1>
<ul>
    <c:forEach var="product" items="${products}">
        <li>${product.name} - ${product.price} <a href="cart?action=add&id=${product.id}">Lägg i kundvagn</a></li>
    </c:forEach>
</ul>
</body>
</html>