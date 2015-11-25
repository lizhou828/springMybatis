<%@ page contentType="text/html;charset=UTF-8" language="java"  isErrorPage="true" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>500:Server internal error!</h1>
    <h2>
        Error Message:<br/>
        A exception was thrown  : <%= exception.getClass() %>:<%= exception.getMessage()%>
    </h2>
</body>
</html>