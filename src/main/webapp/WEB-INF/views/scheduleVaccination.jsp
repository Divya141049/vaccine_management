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
    $( "#datepicker1" ).datepicker();
    $( "#datepicker2" ).datepicker();
  } );
  </script>
</head>
<body>
<c:set var="patient" value="${patient}"/> 
<form action="scheduleVaccination.htm" method="POST">
<fieldset>
<c:choose>
<c:when test="${patient.getFirstDoseDate() == null}">
<label>FIRST DOSE DATE:</label>
<input type="text" name="firstDoseDate" id="datepicker" required><br/>
<label>FIRST DOSE STATUS:</label>
<input type="text" name="firstDoseStatus" value="${patient.getFirstDoseStatus()}" disabled="disabled"><br/>
<label>SECOND DOSE DATE:</label>
<input type="text" name="secondDoseDate" id="datepicker1" value="${patient.getSecondDoseDate()}" disabled="disabled"><br/>
<label>SECOND DOSE STATUS:</label>
<input type="text" name="secondDoseStatus" value="${patient.getSecondDoseStatus()}" disabled="disabled"><br/>
<label>BOOSTER DATE:</label>
<input type="text" name="boosterDate" id="datepicker2" value="${patient.getBoosterDate()}" disabled="disabled"><br/>
<label>BOOSTER STATUS:</label>
<input type="text" name="boosterStatus" value="${patient.getBoosterStatus()}" disabled="disabled"><br/>
<label>SELECT VACCINE:</label>
<select name="vaccine" required>
<c:forEach var="vac" items="${sessionScope.vaccineList}">
    <option value="${vac.vaccineName}">${vac.vaccineName }</option>
</c:forEach>
</select>
<input type="submit" value="SCHEDULE">
</c:when>
<c:when test="${patient.getSecondDoseDate() == null && patient.getFirstDoseStatus() == 'Completed'}">
<label>FIRST DOSE DATE:</label>
<input type="text" name="firstDoseDate" id="datepicker" value="${patient.getFirstDoseDate()}" disabled="disabled"><br/>
<label>FIRST DOSE STATUS:</label>
<input type="text" name="firstDoseStatus" value="${patient.getFirstDoseStatus()}" disabled="disabled"><br/>
<label>SECOND DOSE DATE:</label>
<input type="text" name="secondDoseDate" id="datepicker1" required><br/>
<label>SECOND DOSE STATUS:</label>
<input type="text" name="secondDoseStatus" value="${patient.getSecondDoseStatus()}" disabled="disabled"><br/>
<label>BOOSTER DATE:</label>
<input type="text" name="boosterDate" id="datepicker2" value="${patient.getBoosterDate()}" disabled="disabled"><br/>
<label>BOOSTER STATUS:</label>
<input type="text" name="boosterStatus" value="${patient.getBoosterStatus()}" disabled="disabled"><br/>
<label>SELECT VACCINE:</label>
<select name="vaccine" required>
<c:forEach var="vac" items="${sessionScope.vaccineList}">
    <option value="${vac.vaccineName}">${vac.vaccineName }</option>
</c:forEach>
</select>
<input type="submit" value="SCHEDULE">
</c:when>
<c:when test="${patient.getBoosterDate() == null  && patient.getSecondDoseStatus() == 'Completed'}">
<label>FIRST DOSE DATE:</label>
<input type="text" name="firstDoseDate" id="datepicker" value="${patient.getFirstDoseDate()}" disabled="disabled"><br/>
<label>FIRST DOSE STATUS:</label>
<input type="text" name="firstDoseStatus" value="${patient.getFirstDoseStatus()}" disabled="disabled"><br/>
<label>SECOND DOSE DATE:</label>
<input type="text" name="secondDoseDate" id="datepicker1" value="${patient.getSecondDoseDate()}" disabled="disabled"><br/>
<label>SECOND DOSE STATUS:</label>
<input type="text" name="secondDoseStatus" value="${patient.getSecondDoseStatus()}" disabled="disabled"><br/>
<label>BOOSTER DATE:</label>
<input type="text" name="boosterDate" id="datepicker2" required><br/>
<label>BOOSTER STATUS:</label>
<input type="text" name="boosterStatus" value="${patient.getBoosterStatus()}" disabled="disabled"><br/>
<label>SELECT VACCINE:</label>
<select name="vaccine" required>
<c:forEach var="vac" items="${sessionScope.vaccineList}">
    <option value="${vac.vaccineName}">${vac.vaccineName }</option>
</c:forEach>
</select>
<input type="submit" value="SCHEDULE">
</c:when>
<c:otherwise>
<label>FIRST DOSE DATE:</label>
<input type="text" name="firstDoseDate" id="datepicker" value="${patient.getFirstDoseDate()}" disabled="disabled"><br/>
<label>FIRST DOSE STATUS:</label>
<input type="text" name="firstDoseStatus" value="${patient.getFirstDoseStatus()}" disabled="disabled"><br/>
<label>SECOND DOSE DATE:</label>
<input type="text" name="secondDoseDate" id="datepicker1" value="${patient.getSecondDoseDate()}" disabled="disabled"><br/>
<label>SECOND DOSE STATUS:</label>
<input type="text" name="secondDoseStatus" value="${patient.getSecondDoseStatus()}" disabled="disabled"><br/>
<label>BOOSTER DATE:</label>
<input type="text" name="boosterDate" id="datepicker2" id="datepicker" value="${patient.getBoosterDate()}" disabled="disabled"><br/>
<label>BOOSTER STATUS:</label>
<input type="text" name="boosterStatus" value="${patient.getBoosterStatus()}" disabled="disabled"><br/>
<input type="submit" value="SCHEDULE" disabled="disabled">
</c:otherwise>
</c:choose>
</fieldset>
</form>
</body>
</html>