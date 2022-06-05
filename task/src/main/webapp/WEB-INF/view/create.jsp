<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>従業員登録</title>
</head>
<body>
<div class="container">

      <form action="./valid" method="post">
        <div class="form-group">
          <label>姓</label>
          <input type="text" name="family_name" class="form-control">
        </div>
        <div class="form-group">
          <label>名</label>
          <input type="text" name="last_name" class="form-control">
        </div>
        <div class="form-group">
          <label>生年月日</label>
          <input type="text" name="birth" class="form-control">
        </div>
        <div class="form-group">
          <label>所属部署</label>
          <select class="form-control" name = "d_value">
  			<option value = "a">a</option>
  			<option value = "b">b</option>
  			<option value = "c">c</option>
  			<option value = "d">d</option>
  			<option value = "e">e</option>
  			<option value = "f">f</option>
		  </select>
        </div>
         <div class="form-group">
          <label>入社日</label>
          <input type="date" name="joined_day" class="form-control">
        </div>
        <div class = "mx-auto">
        <button class="btn btn-primary" type="submit" name="addEmployee">確認</button>
        </div>
      </form>
      <% if(request.getAttribute("error_msg")!=null){%>
      	<%=request.getAttribute("error_msg") %>
      <%}%>

</div>
</body>
</html>