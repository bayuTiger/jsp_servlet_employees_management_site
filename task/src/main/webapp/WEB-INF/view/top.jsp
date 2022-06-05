<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.EmployeeBean"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<EmployeeBean> beanList = (ArrayList<EmployeeBean>) request.getAttribute("beanList");
	int count = (Integer) request.getAttribute("count"); 
	
	String sort_icon = (String) session.getAttribute("sort_icon");
	int click_count = (Integer) session.getAttribute("click_count");
	String sort_name = (String) session.getAttribute("sort_name");

	int limit = (Integer)session.getAttribute("limit");
	int offset = (Integer)session.getAttribute("offset");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>従業員一覧</title>
</head>
<body>

	<div class="container">
		<div class = "col-md-6">
			<h1>社員情報一覧</h1>
		</div>
		
		<div class = "col-md-6">
			<form class = "col-md-6" action = "./create" method = "get">
			  <button class="btn btn-primary" type="submit">登録＞</button>
			</form>
		</div>
		
		<fieldset>
		<legend>社員検索</legend>
			<form action = "./top" method = "get">
				<div class = "form-group">
					<label>姓</label>
					<input type = "text" name = "like_family_name" class = "form-control">
				</div>
				<div class = "form-group">
					<label>名</label>
					<input type = "text" name = "like_last_name" class = "form-control">
				</div>
				<div class = "form-group">
					<label>入社日</label>
					<input type = "date" name = "after_joined_day" class = "form-control">
				</div>
				<div class = "form-group">
					<label>所属部門</label>
					<select name = "like_d_name" class = "form-control">
					  <option value = "" selected>所属部門を選択してください</option>
					  <option value = "a">a</option>
					  <option value = "b">b</option>
					  <option value = "c">c</option>
					  <option value = "d">d</option>
					  <option value = "e">e</option>
					  <option value = "f">f</option>
				</div>
				<div class = "form-group">
					<input type = "checkbox" class="form-check-input" name = "not_null_left_day">
					<label class="form-check">退社済を含む</label>
				</div>
				<button class="btn btn-primary" type="submit" name="serch">検索</button>
			</form>
			</fieldset>
		
		<div class="row">	
			<table class="table">
				<tbody>
					<tr class="bg-dark text-white text-center">
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_family_name">姓 <%= sort_name.equals("e.family_name") ? sort_icon : "" %></button>
								<%
									click_count ++;
									session.setAttribute("click_count", click_count);
								%>
							</th>
						</form>
						
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_last_name">名 <%= sort_name.equals("e.last_name") ? sort_icon : "" %></button>
							</th>
						</form>
						
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_birth">生年月日 <%= sort_name.equals("e.birth") ? sort_icon : "" %></button>
							</th>
						</form>
												
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_joined_day">入社日 <%= sort_name.equals("e.joined_day") ? sort_icon : "" %></button>
							</th>
						</form>
									
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_d_name">所属部門 <%= sort_name.equals("d.name") ? sort_icon : "" %></button>
							</th>
						</form>
									
						<form action = "./top" method = "get">
							<th scope="col">
								<button type = "submit" name = "sort_left_day">退社日 <%= sort_name.equals("e.left_day") ? sort_icon : "" %></button>
							</th>
						</form>
									
						<th scope="col"></th>			
					</tr>
					<% for(EmployeeBean bean : beanList){ %>
						<tr class="text-center">
							<form action = "./update" method = "get">
								<input type = "hidden"  name = "id" value = <%= bean.getId() %>>
									<td><%= bean.getFamilyName() %></td>
									<td><%= bean.getLastName() %></td>
									<td><%= bean.getBirth() %></td>
									<td><%= bean.getJoinedDay() %></td>
									<td><%= bean.getDepartmentName() %></td>
									<% if(bean.getLeftDay() != null){ %>
									<td><%= bean.getLeftDay() %></td>
									<% }else{ %>
										<td></td>
									<%} %>
									<td>
										<input type = "submit" value = "編集する">
									</td>
							</form>
						</tr>
					<% } // endfor %>
				</tbody>
			</table>
		</div>

		<form action = "./top" method = "get">
			
			<% if(offset - 4 > 0) { %>
		    	<button type = "submit" class = "btn btn-outline-primary" name = "prev">前へ</button>
		    <% } %>
		    
		    <% if(offset + 4 < count) { %>
		   	 <button type = "submit" class = "btn btn-outline-primary" name = "next">次へ</button>
		    <% } %>
		    
		</form>
		
	</div>

</body>
</html>