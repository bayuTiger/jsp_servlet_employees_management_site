<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <title>結果画面</title>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <h1>結果画面</h1>
        <br />
        <p><%=msg %></p>
        <br />
        <a href="./top">アイテム一覧に戻る</a>
      </div>
    </div>
  </body>
</html>