<%@ page import="java.util.List" %>
<%@ page import="com.example.webshop.dto.CartDTO" %>
<%@ page import="com.example.webshop.dto.CartItemDTO" %>
<%@ page import="com.example.webshop.dto.ProductDTO" %>
<%@ include file="header.jsp" %>

<%
    CartDTO cart = (CartDTO) request.getSession().getAttribute("cart");
    List<CartItemDTO> cartItems = cart != null ? cart.getItems() : null;

    if (cartItems != null && !cartItems.isEmpty()) {
%>
<h1>Your Shopping Cart</h1>
<table>
    <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
        <th>Action</th>
    </tr>
    <%
        double total = 0.0;
        for (CartItemDTO item : cartItems) {
            double itemTotal = item.getProduct().getPrice().doubleValue() * item.getQuantity();
            total += itemTotal;
    %>
    <tr>
        <td><%= item.getProduct().getName() %></td>
        <td><%= item.getQuantity() %></td>
        <td>$<%= item.getProduct().getPrice() %></td>
        <td>$<%= itemTotal %></td>
        <td>
            <form action="RemoveFromCartServlet" method="post">
                <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                <input type="submit" value="Remove">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h2>Total: $<%= total %></h2>
<%
} else {
%>
<p>Your cart is empty.</p>
<%
    }
%>

