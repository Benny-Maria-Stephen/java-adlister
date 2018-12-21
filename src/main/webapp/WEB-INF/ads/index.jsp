<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2><a href="/view-ad?adId=${ad.id}"><c:out value="${ad.title}"/></a></h2>
            <p><c:out value="${ad.description}"/></p>
            <p>Posted by: <c:out value="${user.username}"/></p>
            <c:forEach var="category" items="${ad.categories}">
                <p>${category}</p>
            </c:forEach>
        </div>
    </c:forEach>
</div>

</body>
</html>
