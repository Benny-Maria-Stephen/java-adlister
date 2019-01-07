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
                <input id="title" name="title" class="form-control" type="text" value="${ad.title}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text">${ad.description}</textarea>
            </div>

            <%--check if that value in the categories array list exists, if it does then set the value else set no value--%>
            <div class="form-group">
                <label for="category1">category</label>
                <input id="category1" name="category1" class="form-control" type="text" value="${ad.categories.get(0)}">
            </div>
            <div class="form-group">
                <label for="category2">category</label>
                <input id="category2" name="category2" class="form-control" type="text" value="${ad.categories.get(1)}">
            </div>
            <div class="form-group">
                <label for="category3">category</label>
                <input id="category3" name="category3" class="form-control" type="text" value="${ad.categories.get(2)}">
            </div>
            <div class="form-group">
                <label for="category4">category</label>
                <input id="category4" name="category4" class="form-control" type="text" value="${ad.categories.get(3)}">
            </div>
            <%--<div class="form-group">--%>
                <%--<label for="category5">category</label>--%>
                <%--<input id="category5" name="category5" class="form-control" type="text" value="${ad.categories.get(4)}">--%>
            <%--</div>--%>

    <%--Turned input tag to button tag so that we can use value and name. This makes it easier to grab
        value pairs in the servlet--%>
        <button type="submit" class="btn btn-block btn-primary" value="${sessionScope.editAdId}" name="adToEdit">Submit Edit</button>
    </form>
</div>
</body>
</html>

