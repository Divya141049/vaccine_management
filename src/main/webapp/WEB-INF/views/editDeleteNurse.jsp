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
<form action="editDeleteNurse.htm" method="POST">
<c:set var="nurse" value="${nurse}"/> 
		<fieldset>
		<label>NAME:</label>
		<input type="text" name="name" value="${nurse.getName() }" required/><br/>
		<label>ADDRESS:</label>
 		<input type="text" name="address" value="${nurse.getAddress() }" required/><br/>
 		<label>PHONE NUMBER:</label>
 		<input type="text" name="contact" value="${nurse.getContact() }" required/><br/>
 		<label>EMAIL ID:</label>
 		<input type="text" name="email" value="${nurse.getEmail() }" disabled="disabled"/><br/>
 		<input type="hidden" name="id" value="${nurse.getIdnurse() }"/><br/>
 		</fieldset>
		<input type="submit" name="sub" value="EDIT">
		<input type="submit" name="sub" value="DELETE">
	</form>
</body>
</html>