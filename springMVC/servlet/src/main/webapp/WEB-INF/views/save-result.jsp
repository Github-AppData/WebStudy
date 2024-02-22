<%@ page import="hello.servlet.domain.member.Member" %><%-- Make Study. 26강 MVC 패턴 (적용) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title>Title</title>
</head>
<body>
<성공></성공>
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">Main</a>
</body>
</html>
