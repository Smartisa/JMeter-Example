<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <input type="text" name="inputValue">
    <button type="submit"> Submit</button>
</form>
</body>
</html>