<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>HOSPITAL NAME</b></td>
                <td><b>ADDRESS</b></td>
                <td><b>CONTACT</b></td>
            </tr>
            <c:forEach var="adv" items="${hospitals}">
                <tr>
                    <td>${adv.name}</td>
                    <td>${adv.address}</td>
                    <td>${adv.contact}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>