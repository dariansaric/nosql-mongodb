<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 04.01.19.
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <p>slika još nije dostupna</p>
    <p>ovdje će ići komentari</p>
</c:forEach>
</body>
</html>
