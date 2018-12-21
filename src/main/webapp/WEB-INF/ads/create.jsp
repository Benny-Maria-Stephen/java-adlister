<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"></textarea>
            </div>
            <div class="form-group">
                <label for="category1">category</label>
                <input id="category1" name="category1" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="category2">category</label>
                <input id="category2" name="category2" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="category3">category</label>
                <input id="category3" name="category3" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="category4">category</label>
                <input id="category4" name="category4" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="category5">category</label>
                <input id="category5" name="category5" class="form-control" type="text">
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
