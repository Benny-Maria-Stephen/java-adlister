<%--
  Created by IntelliJ IDEA.
  User: montserratvela
  Date: 12/17/18
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ad View</title>
</head>
<body>
<%--This page is meant to display the information of one ad--%>

<h2><c:out value="${ad.title}"/></h2>
<p><c:out value="${ad.description}"/></p>
<p>Posted by: <c:out value="${poster}"/></p>

</body>
</html>
