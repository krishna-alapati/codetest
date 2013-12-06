<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Weather Forecast</title>
<style type="text/css">
.error {color:red;}
</style>
</head>

<body>
<h2 align="center">Weather Forecast</h2>
<form:form method="post" modelAttribute="forecastInfo" action="forecast">
 
    <table align='center'>
    <tr>
        <td><form:label path="zipcode">Enter the zipcode: </form:label></td>
        <td><form:input path="zipcode" maxlength="5" autofocus="true"/>
        
    </tr>
    <tr><td></td><td><form:errors path='zipcode' cssClass='error'/></td></tr>
    <c:if test="${ not empty forecastInfo.city}">
    	<tr>
        	<td><form:label path="city">City</form:label></td>
        	<td><form:input path="city" disabled="true"></form:input></td>
    	</tr>
    </c:if>
    <c:if test="${ not empty forecastInfo.state}">
    	<tr>
        	<td><form:label path="city">State</form:label></td>
        	<td><form:input path="state" disabled="true"></form:input></td>
    	</tr>
    </c:if>
    <c:if test="${ not empty forecastInfo.temperature}">
    	<tr>
        	<td><form:label path="city">Temperature</form:label></td>
        	<td><form:input path="temperature" disabled="true"></form:input></td>
    	</tr>
    </c:if>
    <c:if test="${ not empty forecastInfo.errorDescription}">
    	<tr>
        	<td colspan="2"><b class='error'>Error: ${forecastInfo.errorDescription} for zipcode ${forecastInfo.zipcode}</b></td>
        	
    	</tr>
    </c:if>
    
    <tr>
        <td><input type="submit" value="Submit"/></td>
        <td><a href="forecast">Clear</a></td>
    </tr>
    
    
    
</table> 
     
</form:form>
</body>
</html>