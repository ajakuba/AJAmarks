<page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1">
<taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core">


<html>
<body>
	<h1>${headerMessage}</h1>

	<h3>Congratulations!! the Engineering college has processed your Application form successfully</h3>

	<h2>Details submitted by you:: </h2>

	<table>
		<tr>
			<td>Student Name :</td>
			<td>${student1.userName}</td>
		</tr>
		<tr>
			<td>Student First Name :</td>
			<td>${student1.firstName}</td>
		</tr>
		<tr>
        	<td>Student Last Name:</td>
        	<td>${student1.lastName}</td>
        </tr>

	</table>

</body>
</html>