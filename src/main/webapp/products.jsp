<%@ page import="java.util.List" %>
<%@ page import="com.example.webshop.model.Product" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.ServletException" %>

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
            <%--@declare id="quantity"--%><input type="hidden" name="productId" value="<%= product.getId() %>" />
            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" min="1" value="1" required />
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

