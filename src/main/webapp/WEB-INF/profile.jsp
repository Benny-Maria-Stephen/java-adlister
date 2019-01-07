<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 10px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <h2>Here are your ads: </h2>
        <div class="tableList">
            <table>

            <c:forEach var="ad" items="${usersAds}">
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Edit/Delete</th>
                    </tr>
                    <tr>
                        <td>${ad.getTitle()}</td>
                        <td>${ad.getDescription()}</td>
                        <td>
                            <%--changed the method to get because we want to grab information and return it (the editAd.jsp)--%>
                            <form action="/ads/edit" method="get" >
                            <button type="submit" class="btn btn-primary btn-block" value="${ad.id}" name="edit">Edit</button>
                        </form>
                            <form action="/ads/delete" method="post">
                                    <%--<input type="submit" class="btn btn-primary btn-block" name="delete" value="Delete">--%>
                                <button type="submit" class="btn btn-primary btn-block" value="${ad.id}" name="delete">Delete</button>
                            </form>
                        </td>
                    </tr>
            </c:forEach>

            </table>
                <%--<h4>${ad.getTitle()}</h4>--%>
                <%--<p>${ad.getDescription()}</p>--%>

        </div>
    </div>

</body>
</html>
