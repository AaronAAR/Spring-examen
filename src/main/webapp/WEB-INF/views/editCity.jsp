<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit City</title>
</head>
<body>
<h1>${headerMessage}</h1>
	
        <form:form method="POST" action="editCity" modelAttribute="city">
        
        	<form:hidden path="city_id" /> 
             <table>
                <tr>
                    <td><form:label path="city_id">City ID</form:label></td>
                    <td><form:input path="city_id"/></td>
                </tr>
                <tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <td><form:label path="state_id">State ID</form:label></td>
                    <td><form:input path="state_id"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
</body>
</html>