<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession currentSession = request.getSession();
    currentSession.invalidate(); // Clear session but keep cart in the database
    response.sendRedirect("login.jsp");
%>
