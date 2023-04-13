<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>従業員登録画面</title>
		<link rel="stylesheet" href="css/btn.css">
		<link rel="stylesheet" href="css/header.css">
		<script>
			function countCheck() {
				if (document.empForm.id.value.length > 2) {
					alert("社員番号は2文字以下で入力してください");
					return false
				}
				return true;
			}
		
			function valueCheck() {
				if (documet.empForm.id.value=="" && document.empForm.name.value=="") {
					alert("社員番号と社員名どちらも入力してください");
					return false;
					
				} else if (document.empForm.id.value=="") {
					alert("社員番号を入力してください");
					return false;
					
				} else if (document.empForm.name.value=="") {
					alert("社員名を入力してください。");
					return false;
				}
				
				return true;
			}
		</script>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<h2>従業員登録画面</h2>
		<form action="EmployeeServlet" name="empForm" method="post" onsubmit="return valueCheck()">
		
			<label for="id">社員番号 ※２桁の数字で入力してください　例）02、11</label><br>
			<input type="text" name="id" placeholder="社員番号"><br>
			
			<label for="name">社員名：</label><br>
			<input type="text" name="name" placeholder="社員名"><br>
			
			<%-- セレクトボックスで作成 --%>
			<label for="age">年齢：</label><br>
			<select name="age" size="1">
			<% for (int i = 1; i < 101; i++) { %>
				<option value="<%= i %>"><%= i %>歳</option>
			<% } %>
			</select>
			<br>
			
			<input class="form-btn" type="submit" value="社員登録ボタン" onclick="return countCheck()">
		</form>
	</body>
</html>