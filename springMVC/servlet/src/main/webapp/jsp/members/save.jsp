<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Make Study. 24강 JSP로 회원 관리 웹 애플리케이션 만들기 --%>

<%-- 최근 파일 보는 단축키: command + E --%>

<%-- Java 코드를 넣을 수 있는 표시 '<% %>' --%>
<%
    // request, response 자동 사용 가능
    // -> jsp도 결국에는 나중에 서블릿으로 변환이 되어서 사용이 되어진다. = service 로직이 그대로 실행된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

    /// 파라미터 값을 꺼내는 코드 - GET, POST 상관없다.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age")); // age는 정수 -> Integer.parseInt 형 변횐

    /// 비즈니스 로직 Start !
    Member member = new Member(username, age); // 등록
    memberRepository.save(member); // 저장
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>id = <%= member.getId()%></li>
        <li>username = <%= member.getUsername()%></li>
        <li>age = <%= member.getAge()%></li>
    </ul>
<a href="/index.html">Main</a>
</body>
</html>
