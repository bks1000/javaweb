<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page language="java" import="com.june.dto.Book"%>
<%@include file="base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表</title>
<%@include file="base/jscss.jsp" %>
</head>
<body>
	<div class="btn-group">
		<button type="button" class="btn btn-default" onclick="query()">查询</button>
		<button class="btn btn-default" data-toggle="modal"
			data-target="#mainModal" href="${ctx}/book/add.do">新增</button>
		<button id="btnUpdate" type="button" class="btn btn-default"
			onclick="btnUpdate()">修改</button>
		<button type="button" class="btn btn-default" onclick="btnDel()">删除</button>
	</div>
	<table class="table table-hover">
		<thead class="thead-inverse">
			<tr scope="row">
				<th>选择</th>
				<th>行号</th>
				<th>书名</th>
				<th>作者</th>
				<th>出版日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${books }" varStatus="status">
				<tr scope="row">
					<td><input id="txt${item.bookId}" type="checkbox"
						onclick="chk(this)" /></td>
					<td>${status.index+1 }</td>
					<td>${item.bookName }</td>
					<td>${item.author }</td>
					<td>${item.publishing }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>