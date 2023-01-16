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
<c:set var="patient" value="${patient}"/> 
<form action="patientPageSchedule.htm" method="POST">
<h4>WELCOME ${sessionScope.user.getFirstName()} ${sessionScope.user.getLastName() }</h4><br/><br/>
<p>FIRST DOSE DATE: ${patient.getFirstDoseDate() }</p><br/>
<p>FIRST DOSE STATUS: ${patient.getFirstDoseStatus() }</p><br/>
<p>FIRST DOSE VACCINE: ${patient.getFirstDoseVaccine() }</p><br/>
<p>SECOND DOSE DATE: ${patient.getSecondDoseDate() }</p><br/>
<p>SECOND DOSE STATUS: ${patient.getSecondDoseStatus() }</p><br/>
<p>FIRST DOSE VACCINE: ${patient.getFirstDoseVaccine() }</p><br/>
<p>BOOSTER DATE: ${patient.getBoosterDate() }</p><br/>
<p>BOOSTER STATUS: ${patient.getBoosterStatus() }</p><br/>
<p>BOOSTER VACCINE: ${patient.getBoosterVaccine() }</p><br/>
<label>SELECT HOSPITAL:</label>
<select name="hospital" required>
<c:forEach var="hosp" items="${sessionScope.hospitals}">
    <option value="${hosp.email}">${hosp.name }</option>
</c:forEach>
</select>
<input type="submit" value="SCHEDULE VACCINATION"><br/>
</form>
<form action="patientPageUpdate.htm" method="POST">
<input type="submit" value="EDIT PROFILE">
</form>
</body>
</html>