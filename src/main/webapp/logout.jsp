<%@ page import="javax.servlet.http.HttpSession" %>
<%
    // Create a new variable for the session
    HttpSession currentSession = request.getSession();

    // Invalidate the session to log out the user
    currentSession.invalidate(); // This removes the user from the session

    // Redirect to the home page or login page
    response.sendRedirect("login.jsp"); // Change to the desired page
%>
