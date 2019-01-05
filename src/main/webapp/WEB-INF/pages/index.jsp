<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 04.01.19.
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<jsp:useBean id="page" scope="request" type="java.lang.Integer"/>--%>
<html>
<head>
    <title>Index</title>
</head>
<body>
<jsp:useBean id="news" scope="request" type="java.util.List"/>
<c:forEach var="n" items="${news}">
    <p>${n.title}</p>
    <p>${n.text}</p>
    <p>${n.author}</p>
    <img src="${n.picture}" alt="slika nije dostupna">
    <ul>
        <c:forEach var="c" items="${n.comments}">
            <li>${c}</li>
        </c:forEach>
    </ul>
    <%--todo riješi kako prikazati komentare na pametan način--%>
    <form action="" method="post">
        <input type="hidden" name="id" value="${n.id}">
        <input type="hidden" name="page" value="${page}">
        <input type="text" name="comment" placeholder="komentar...">
        <input type="submit">
    </form>
</c:forEach>

<form action="" method="get">
    <input type="hidden" name="page" value="<%= Integer.valueOf(String.valueOf(request.getAttribute("page"))) - 1%>">
    <input type="submit" value="prethodna stranica">
</form>
${page}
<form action="" method="get">
    <input type="hidden" name="page" value="<%= Integer.valueOf(String.valueOf(request.getAttribute("page"))) + 1%>">
    <input type="submit" value="slijedeća stranica">
</form>
</body>
</html>
