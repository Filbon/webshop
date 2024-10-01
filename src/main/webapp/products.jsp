<%@ page import="com.example.webshop.model.Product" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
%>
<h1>Product List</h1>
<ul>
    <%
        for (Product product : products) {
    %>
    <li>
        <strong><%= product.getName() %></strong> - $<%= product.getPrice() %>
        <form action="AddToCartServlet" method="post">
            <input type="hidden" name="productId" value="<%= product.getId() %>" />
            <input type="number" name="quantity" value="1" min="1" />
            <button type="submit">Add to Cart</button>
        </form>
    </li>
    <%
        }
    %>
</ul>
<%
} else {
%>
<p>No products available.</p>
<%
    }
%>


