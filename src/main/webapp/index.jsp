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
    <label>
        <input type="text" name="name" placeholder="请输入姓名">
        <input type="text" name="sex" placeholder="请输入性别">
        <input type="text" name="age" placeholder="请输入年龄">
    </label>

    <button type="submit"> Submit</button>
</form>
</body>
</html>