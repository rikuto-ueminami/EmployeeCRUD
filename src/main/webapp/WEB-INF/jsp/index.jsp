<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>従業員一覧</title>
		<link rel="stylesheet" href="css/btn.css">
		<link rel="stylesheet" href="css/header.css">
		<style>
		.table1 {
		  border: 1px solid gray;
		}
		.table1 th, .table1 td {
		  border: 1px solid gray;
		}
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<h1>従業員一覧</h1>
		<table class="table1">
			<thead>
				<tr>
					<th>社員番号</th>
					<th>名前</th>
					<th>年齢</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employee" items="${empList}">
					<tr>
						<td><c:out value="${employee.id }" /></td>
						<td><c:out value="${employee.name }" /></td>
						<td><c:out value="${employee.age }" /></td>
						<td><a href="EmployeeEdit?empId=${employee.id}" class="edit-btn">編集</a></td>
						<td><a href="EmployeeDelete?empId=${employee.id}" class="delete-btn">削除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>