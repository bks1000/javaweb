<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="com.june.dto.BookType"%>
<%@include file="base/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书类型列表</title>
<script type="text/javascript" src="${ctx }/static/jquery/dist/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/static/bootstrap/dist/js/bootstrap.min.js" charset="utf-8"></script>
<link rel="Stylesheet" type="text/css" href="${ctx }/static/bootstrap/dist/css/bootstrap.min.css" />
<!-- jstl Foreach    http://blog.csdn.net/liu78778/article/details/3973786 -->
<script type="text/javascript">
	//只能选择一个
	function chk(obj){
		var state = obj.checked;
		$(":checkbox").each(function(i){
			  this.checked=false;
		 });
		obj.checked = state;
	}
	//获取选中行的主键ID
	function getCheckID(){
		var ret;
		$(":checkbox").each(function(){
			if(this.checked){
				var id = this.id;
				//alert(id);
				ret = id.slice(3);
				return false;//退出循环
			}
		});
		return ret;
	}
	
	function btnUpdate(){
		var id = getCheckID();
		alert(id);
	}
	
	function btnAdd() {

	}
</script>
</head>
<body>
<div class="content">
	<div class="btn-group">
	  <button type="button" class="btn btn-default">查询</button>
	  <input type="button" class="btn btn-default" value="新增" onclick="btnAdd()"/>
	  <button id="btnUpdate" type="button" class="btn btn-default" onclick="btnUpdate()">修改</button>
	  <button type="button" class="btn btn-default">删除</button>
	</div>
	<table class="table table-hover">
		<thead class="thead-inverse">
			<tr scope="row">
				<th>选择</th>
				<th>行号</th>
				<th>ID</th>
				<th>类型名称</th>
				<th>PID</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${types }" varStatus="status">
				<tr scope="row">
					<td><input id="txt${item.id}" type="checkbox" onclick="chk(this)"/></td>
					<td>${status.index+1 }</td>
					<td>${item.id }</td>
					<td>${item.typeName }</td>
					<td>${item.parentId }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>