<%@ page import="java.util.List" %>
<%@ page import="com.example.webshop.model.Cart" %>
<%@ page import="com.example.webshop.model.CartItem" %>
<%@ page import="com.example.webshop.model.Product" %>

<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    List<CartItem> cartItems = cart != null ? cart.getItems() : null;

    if (cartItems != null && !cartItems.isEmpty()) {
%>
<h1>Your Shopping Cart</h1>
<table>
    <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
    </tr>
    <%
        double total = 0.0;
        for (CartItem item : cartItems) {
            double itemTotal = item.getProduct().getPrice().doubleValue() * item.getQuantity();
            total += itemTotal;
    %>
    <tr>
        <td><%= item.getProduct().getName() %></td>
        <td><%= item.getQuantity() %></td>
        <td>$<%= item.getProduct().getPrice() %></td>
        <td>$<%= itemTotal %></td>
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

