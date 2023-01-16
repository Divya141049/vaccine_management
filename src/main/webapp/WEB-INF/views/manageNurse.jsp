<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="nurse.htm" method="post">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>NAME</b></td>
                <td><b>ADDRESS</b></td>
                <td><b>CONTACT</b></td>
                <td><b>EMAIL</b></td>
            </tr>
            <c:forEach var="nurse" items="${nurses}">
                <tr>
                    <td>${nurse.name}</td>
                    <td>${nurse.address}</td>
                    <td>${nurse.contact}</td>
                    <td>${nurse.email}</td>
                </tr>
            </c:forEach>
        </table> <br/><br/>
        <input type="submit" name="sub" value="ADD NURSE"/> <br/><br/>
		<input type="submit" name="sub" value="EDIT/DELETE NURSE"/> <br/><br/>
	</form>
</body>
</html>