<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form action="pharmacy.htm" method="post">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>PHARMACY NAME</b></td>
                <td><b>ADDRESS</b></td>
                <td><b>CONTACT</b></td>
                <td><b>EMAIL</b></td>
            </tr>
            <c:forEach var="pharm" items="${pharmacies}">
                <tr>
                    <td>${pharm.name}</td>
                    <td>${pharm.address}</td>
                    <td>${pharm.contact}</td>
                    <td>${pharm.email}</td>
                </tr>
            </c:forEach>
        </table> <br/><br/>
        <input type="submit" name="sub" value="ADD PHARMACY"/> <br/><br/>
		<input type="submit" name="sub" value="EDIT/DELETE PHARMACY"/> <br/><br/>
	</form>
</html>