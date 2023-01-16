<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<form action="user.htm" method="POST">
		<fieldset>
		<label>FIRST NAME:</label>
		<input type="text" name="FirstName"/><br/>
		<label>LAST NAME:</label>
 		<input type="text" name="LastName"/><br/>
 		<label>PHONE NUMBER:</label>
 		<input type="text" name="Phone"/><br/>
 		<label>EMAIL ID:</label>
 		<input type="text" name="Email"/><br/>
 		<label>PASSWORD:</label>
 		<input type="password" name="Password"/>
 		</fieldset>
		<input type="submit" value="Register">
		<input type="hidden" name="sub" value="reg"/>
	</form>
</body>
</html>