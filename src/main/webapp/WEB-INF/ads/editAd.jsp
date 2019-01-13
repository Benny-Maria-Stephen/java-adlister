<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Edit an Ad</h1>
    <form action="/ads/edit" method="post">
        <%--<jsp:include page="/WEB-INF/partials/adForm.jsp"/>--%>
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" value="${adToEdit.title}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text">${adToEdit.description}</textarea>
            </div>

            <%--check if that value in the categories array list exists, if it does then set the value else set no value--%>
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="${category}" value="true" ><span>${category}</span>
                <%--<c:choose>--%>
                    <%--<c:when test="${category}">--%>
                        <%--<input type="checkbox" name="${category}" value="true" checked><span>${category}</span>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<input type="checkbox" name="${category}" value="true"><span>${category}</span>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            </c:forEach>

    <%--Turned input tag to button tag so that we can use value and name. This makes it easier to grab
        value pairs in the servlet--%>
        <button type="submit" class="btn btn-block btn-primary" value="${sessionScope.editAdId}" name="adToEdit">Submit Edit</button>
    </form>
</div>
</body>
</html>

