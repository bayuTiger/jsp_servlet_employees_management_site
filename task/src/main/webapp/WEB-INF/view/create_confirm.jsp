<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

// セッションからデータを取得
String family_name = (String)session.getAttribute("family_name");
String last_name = (String)session.getAttribute("last_name");
String birth = session.getAttribute("birth").toString();
String d_value = (String)session.getAttribute("d_value");
String joined_day = session.getAttribute("joined_day").toString();

session.setAttribute("family_name", family_name);
session.setAttribute("last_name", last_name);
session.setAttribute("d_value", d_value);
session.setAttribute("birth", birth);
session.setAttribute("joined_day", joined_day);

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>新規登録確認画面</title>
</head>
<body>

	<div class = "container">
	    <form action="./top" method="post">
	    
	        <div class="form-group">
	          <label>姓</label>
	          <p><%= family_name %></p>
	        </div>
	        
	        <div class="form-group">
	          <label>名</label>
	          <p><%= last_name %></p>
	        </div>
	        
	        <div class="form-group">
	          <label>所属部署</label>
	          <p><%= d_value %></p>
	        </div>
	        
	        <div class="form-group">
	          <label>生年月日</label>
	          <p><%= birth %></p>
	        </div>
	        
	         <div class="form-group">
	          <label>入社日</label>
	          <p><%= joined_day %></p>
	        </div>
	        
	        <div class = "mx-auto">
	        <button class="btn btn-primary" type="submit" name="addEmployee">登録</button>
	        </div>
	        
      </form>
	</div>
	
</body>
</html>