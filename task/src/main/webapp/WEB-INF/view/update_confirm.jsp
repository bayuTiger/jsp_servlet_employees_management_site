<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

// セッションからデータを取得
int id = (Integer)session.getAttribute("id");
String family_name = (String)session.getAttribute("family_name");
String last_name = (String)session.getAttribute("last_name");
String birth = session.getAttribute("birth").toString();
String d_value = session.getAttribute("d_value").toString();
String joined_day = session.getAttribute("joined_day").toString();

String left_day = null;
if(session.getAttribute("left_day") != null){
	left_day = session.getAttribute("left_day").toString();
}

session.setAttribute("family_name", family_name);
session.setAttribute("last_name", last_name);
session.setAttribute("birth", birth);
session.setAttribute("d_value", d_value);
session.setAttribute("joined_day", joined_day);
session.setAttribute("left_day", left_day);

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>更新確認画面</title>
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
	          <label>生年月日</label>
	          <p><%= birth %></p>
	        </div>
	        
	        <div class="form-group">
	          <label>所属部門</label>
	          <p><%= d_value %></p>
	        </div>
	        
	         <div class="form-group">
	          <label>入社日</label>
	          <p><%= joined_day %></p>
	        </div>
	        
	        <% if(left_day != null){ %>
	        <div class="form-group">
	          <label>退社日</label>
	          <p><%= left_day %></p>
	        </div>
	        <% } %>
	        
	        <button class="btn btn-primary" type="submit" name="updateEmployee" onclick = "return Check()">更新</button>
	        
      </form>
	</div>
	
<script>
	function Check(){
		if(confirm("更新してもよろしいですか?") == false){
			alert("更新を取り止めました");
			return false;
		}
	}
</script>
</body>
</html>