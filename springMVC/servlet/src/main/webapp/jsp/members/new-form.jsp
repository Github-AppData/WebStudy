<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Make Study. 24강 JSP로 회원 관리 웹 애플리케이션 만들기 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/jsp/members/save.jsp" method="post">
        username: <input type="text" name="username" />
        age:      <input type="text" name="age" />
        <button type="submit">전송</button>
    </form>
</body>
</html>
