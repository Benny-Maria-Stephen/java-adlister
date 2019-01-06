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
            <jsp:include page="/WEB-INF/partials/adForm.jsp"/>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
