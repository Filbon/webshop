<%@ page import="java.util.List" %>
<%@ page import="com.example.webshop.model.Cart" %>
<%@ page import="com.example.webshop.model.CartItem" %>
<%@ page import="com.example.webshop.model.Product" %>
<%@ include file="header.jsp" %>


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
        <th>Action</th> <!-- Add a new column for actions -->
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
        <td>
            <!-- Form to remove item from cart -->
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

