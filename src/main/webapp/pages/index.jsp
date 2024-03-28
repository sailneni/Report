<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Generate Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
    	rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
    	crossorigin="anonymous">
</head>
<body>

<div class="container-fluid">
<h3 class="text-center pb-3">Report Application</h3>
<form:form action="search" modelAttribute="search" method="POST">
	<table>
		<tr>
			<td>Plan Name:</td>
			<td>
				<form:select path="planName">
					<form:option value="">Select</form:option>
					<form:options items="${names}"/>
				</form:select>
					
			</td>
			<td>Plan Status:</td>
			<td>
				<form:select path="planStatus">
					<form:option value="">Select</form:option>
					<form:options items="${status}"/>
				</form:select>
					
			</td>
			<td>Gender:</td>
			<td>
				<form:select path="gender">
					<form:option value="">Select</form:option>
					<form:option value="Male">Male</form:option>
					<form:option value="Female">Female</form:option>
				</form:select>
					
			</td>
		</tr>
		<tr>
			<td>Start Date:</td>
			<td>
				<form:input path="startDate" type="date" data-date-format="yyyy-mm-dd"/>
			</td>
		
		
			<td>End Date:</td>
			<td>
				<form:input path="endDate" type="date" data-date-format="yyyy-mm-dd"/>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Search" class="btn btn-primary"/>
			</td>
			<td>
				<input type="reset" value="Reset" class="btn btn-secondary"/>
			</td>
		</tr>
	</table>
</form:form>
<hr/>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Holder Name</th>
			<th>Plan Name</th>
			<th>Plan Status</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${index.count}count</td>
			<td>${plan.citizenName}item.citizenName</td>
			<td>${plan.gender}</td>
			<td>${plan.planName}</td>
			<td>${plan.planStatus}</td>
			<td>${plan.planEndDate}</td>
			<td>${plan.planStartDate}</td>
			
		</tr>
		<tr>
		<c:if test="${empty plans}">
			<td colspan="8" style="text-align:center">No Records found</td>
		</c:if>
		</tr>
	</tbody>
</table>
<hr/>


<div class="col mt-2">
Export: <a href="excel" class="btn btn-success me-2">Excel</a><a href="pdf" class="btn btn-warning me-2">Pdf</a>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
		crossorigin="anonymous"></script>
</body>
</html>