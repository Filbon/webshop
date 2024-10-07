<%@ page import="com.example.webshop.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<%
    // Retrieve the list of products as ProductDTOs
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
%>
<h1>Product List</h1>
<ul>
    <%
        // Iterate through the ProductDTOs
        for (ProductDTO product : products) {
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


