<page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1">
<taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core">



<html>
<body>
<h1>${headerMessage}</h1>
<h1>Adding student</h1>
<form action = "/ajamarks/jspapi/student/submitionStudentAdmissionForm.html" method="post">
<p>
Student's userName : <input type="text" name = "userName" />
</p>

<p>
Student's firstName : <input type="text" name = "firstName" />
</p>

<p>
Student's lastName : <input type="text" name = "lastName" />
</p>

<input type = "submit" value="Submit this form by clicking here" />

</form>

</body>
</html>