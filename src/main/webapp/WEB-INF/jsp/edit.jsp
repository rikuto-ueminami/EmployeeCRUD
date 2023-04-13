<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>編集画面</title>
		<link rel="stylesheet" href="css/btn.css">
		<link rel="stylesheet" href="css/header.css">
		<script>
			function valueCheck() {
				if (document.empForm.id.value=="" && document.empForm.name.value=="") {
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
		<h2>従業員編集画面</h2>
		<%-- 変更前の社員番号を送っておいてUPDATEのWHERE句で使いたい --%>
		<form action="EmployeeEdit?empId=${employee.id}" name="empForm" method="post" onsubmit="return valueCheck()">
		
			<label for="id">社員番号 ※数字の部分のみ変更してください</label><br>
			<%-- スコープに保存したEmployeeインスタンスのidフィールドを初期値に設定する --%>
			<input type="text" name="id" value="${employee.id}" placeholder="社員番号"><br>
			
			<%-- スコープに保存したEmployeeインスタンスのnameフィールドを初期値に設定する --%>
			<label for="name">社員名：</label><br>
			<input type="text" name="name" value="${employee.name}" placeholder="社員名"><br>
			
			<%-- セレクトボックスで作成 --%>
			<label for="age">年齢：</label><br>
			<select name="age" size="1">
			<% for (int i = 1; i < 101; i++) { %>
				<option value="<%= i %>"><%= i %>歳</option>
			<% } %>
			</select>
			<br>
			
			<input class="update-btn" type="submit" value="社員情報更新ボタン">
		</form>
	</body>
</html>