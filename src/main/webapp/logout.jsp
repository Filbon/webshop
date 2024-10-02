<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession currentSession = request.getSession();
    currentSession.invalidate();
    response.sendRedirect("login.jsp");
%>
