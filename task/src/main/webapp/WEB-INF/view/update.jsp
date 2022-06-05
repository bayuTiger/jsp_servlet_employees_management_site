<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.EmployeeBean"%>
<%
	EmployeeBean bean = (EmployeeBean) request.getAttribute("bean");
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>従業員更新</title>
</head>
<body>
<div class="container">

      <form action="./valid" method="post">
        <div class="form-group">
          <input type="hidden" name="id" class="form-control" value = <%= bean.getId() %>>
          <label>姓</label>
          <input type="text" name="family_name" class="form-control" value = <%= bean.getFamilyName() %>>
        </div>
        <div class="form-group">
          <label>名</label>
          <input type="text" name="last_name" class="form-control" value = <%= bean.getLastName() %>>
        </div>
        <div class="form-group">
          <label>生年月日</label>
          <input type="text" name="birth" class="form-control" value = <%= bean.getBirth() %> >
        </div>
        <div class="form-group">
          <label>所属部署</label>
          <select class="form-control" name = "d_value">
  			<option value = "a" <%= "a".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>a</option>
  			<option value = "b" <%= "b".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>b</option>
  			<option value = "c" <%= "c".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>c</option>
  			<option value = "d" <%= "d".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>d</option>
  			<option value = "e" <%= "e".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>e</option>
  			<option value = "f" <%= "f".equals(bean.getDepartmentName()) ? "selected = \"selected\"" : "" %>>f</option>
		  </select>
        </div>
         <div class="form-group">
          <label>入社日</label>
          <input type="date" name="joined_day" class="form-control" value = <%= bean.getJoinedDay() %>>
        </div>
        <div class="form-group">
          <label>退社日</label>
    	   <% if(bean.getLeftDay() != null){ %>
          <input type="date" name="left_day" class="form-control" value = <%= bean.getLeftDay() %>>
           <% }else{ %>
		  <input type="text" name="left_day" class="form-control">
		  <%} %>
        </div>
        <div class = "mx-auto">
        <button class="btn btn-primary" type="submit" name="updateEmployee">確認</button>
        </div>
      </form>
      <% if(request.getAttribute("error_msg")!=null){%>
      	<%=request.getAttribute("error_msg") %>
      <%}%>

</div>

</body>
</html>