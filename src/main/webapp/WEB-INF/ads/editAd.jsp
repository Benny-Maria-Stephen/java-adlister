<%--
  Created by IntelliJ IDEA.
  User: fieldsfury
  Date: 2019-01-04
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit an Ad" />
    </jsp:include>
</head>
<body>
<div class="container">
    <h1>Create a new Ad</h1>
    <form action="/ads/edit" method="post">
        <jsp:include page="/WEB-INF/partials/adForm.jsp"/>
    <%--Turned input tag to button tag so that we can use value and name. This makes it easier to grab
        value pairs in the servlet--%>
        <button type="submit" class="btn btn-block btn-primary" value="${sessionScope.editAdId}" name="adToEdit">Submit Edit</button>
    </form>
</div>
</body>
</html>

