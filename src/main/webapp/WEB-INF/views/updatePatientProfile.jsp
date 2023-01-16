<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
</script>
</head>
<body>
<c:set var="patient" value="${patient}"/> 
<form action="updatePatientProfile.htm" method="POST">
		<fieldset>
		<label>FIRST NAME:</label>
		<input type="text" name="firstName" value="${sessionScope.user.getFirstName() }" required/><br/>
		<label>LAST NAME:</label>
 		<input type="text" name="lastName" value="${sessionScope.user.getLastName() }" required/><br/>
 		<label>PHONE NUMBER:</label>
 		<input type="text" name="contact" value="${sessionScope.user.getPhone() }" required/><br/>
 		<label>ADDRESS:</label>
 		<input type="text" name="address" value="${patient.getAddress() }" required/><br/>
 		<label>DATE OF BIRTH:</label>
 		<input type="text" name="dob" id="datepicker" value="${patient.getDob() }" required/><br/>
 		<label>EMAIL:</label>
 		<input type="text" name="email" value="${sessionScope.user.getEmail() }" disabled="disabled"/><br/>
 		</fieldset>
		<input type="submit" value="UPDATE">
	</form>
</body>
</html>