<!-- <frameset cols="200,*">
    <frame src="menu.jsp"     name="menu" noresize/>
    <frame src="contents.jsp" name="contents"/>
</frameset> -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form action="hospital.htm" method="post">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>HOSPITAL NAME</b></td>
                <td><b>ADDRESS</b></td>
                <td><b>CONTACT</b></td>
                <td><b>EMAIL</b></td>
            </tr>
            <c:forEach var="hosp" items="${hospitals}">
                <tr>
                    <td>${hosp.name}</td>
                    <td>${hosp.address}</td>
                    <td>${hosp.contact}</td>
                    <td>${hosp.email}</td>
                </tr>
            </c:forEach>
        </table> <br/><br/>
        <input type="submit" name="sub" value="ADD HOSPITAL"/> <br/><br/>
		<input type="submit" name="sub" value="EDIT/DELETE HOSPITAL"/> <br/><br/>
	</form>
    </body>
</html>