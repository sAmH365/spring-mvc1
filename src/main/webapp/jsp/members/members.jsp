<%@ page import="hello.servelet.domain.member.MemberRepository" %>
<%@ page import="hello.servelet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: samh365
  Date: 2023/03/10
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%
   MemberRepository memberRepository = MemberRepository.Companion.getInstance();
  List<Member> members = memberRepository.findAll();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <%
    for (Member member : members) {
      out.write("<tr>");
      out.write("<td>" + member.getId() + "</td>");
      out.write("<td>" + member.getUsername() + "</td>");
      out.write("<td>" + member.getAge() + "</td>");
      out.write("</tr>");
    }
  %>
  </tbody>
</table>
</body>
</html>
