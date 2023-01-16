<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addNurse.htm" method="POST">
		<fieldset>
		<label>NAME:</label>
		<input type="text" name="name" required/><br/>
		<label>ADDRESS:</label>
 		<input type="text" name="address" required/><br/>
 		<label>PHONE NUMBER:</label>
 		<input type="text" name="contact" required/><br/>
 		<label>EMAIL ID:</label>
 		<input type="text" name="email" required/><br/>
 		<label>PASSWORD:</label>
 		<input type="password" name="password" required/><br/>
 		</fieldset>
		<input type="submit" value="ADD">
	</form>
</body>
</html>