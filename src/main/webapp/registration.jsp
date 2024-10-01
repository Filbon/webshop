<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="RegisterServlet" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Register">
</form>
<%
    String error = request.getParameter("error");
    if (error != null) {
        out.println("<p style='color:red;'>" + error + "</p>");
    }
%>
</body>
</html>
