<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
<%
    String error = request.getParameter("error");
    if (error != null) {
        out.println("<p style='color:red;'>" + error + "</p>");
    }
%>
</body>
</html>
