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
 <form action="updateVaccine.htm" method="POST">
<table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>VACCINE NAME</b></td>
                <td><b>IN-STOCK</b></td>
            </tr>
            <c:forEach var="vaccine" items="${vaccines}">
                <tr>
                    <td>${vaccine.vaccineName}</td>
                    <td>
                    <input type="hidden" name="id" value="${vaccine.idvaccine}">
                    <c:choose>
					<c:when test="${vaccine.inStock == 'Available'}">
					<select name="inStockArr" required>
		  				<option value="Available" selected>Available</option>
				  		<option value="Not Available">Not Available</option>
					</select>
					</c:when>
					<c:otherwise>
					<select name="inStockArr" required>
		  				<option value="Available">Available</option>
				  		<option value="Not Available" selected>Not Available</option>
					</select>
					</c:otherwise>
					</c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table> <br/><br/>
        <input type="submit" value="UPDATE"> <br/><br/>
        </form>
        <form action="addVaccine.htm" method="POST">
        
		<fieldset>
		<label>VACCINE NAME:</label>
		<input type="text" name="name" required/><br/>
 		<label>IN-STOCK:</label>
		<select name="inStock" required>
  			<option value="Available">Available</option>
		  	<option value="Not Available">Not Available</option>
		</select>
 		</fieldset>
		<input type="submit" value="ADD">
	</form>
</body>
</html>