<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page language="java" import="com.june.dto.Book"%>
<%@include file="../base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表</title>
<%@ include file="../base/jscss.jsp" %>
<script type="text/javascript">
	function query() {
		location.reload(true);
	}
	//只能选择一个
	function chk(obj) {
		var state = obj.checked;
		$(":checkbox").each(function(i) {
			this.checked = false;
		});
		obj.checked = state;
	}
	//获取选中行的主键ID
	function getCheckID() {
		var ret="";
		$(":checkbox").each(function() {
			if (this.checked) {
				var id = this.id;
				//alert(id);
				ret = id.slice(3);
				return false;//退出循环
			}
		});
		return ret;
	}
	
	function btnUpdate() {
		var id = getCheckID();
		if (id == undefined || id == "undefined" ||id=="") {
			alert("请选择要修改的数据！");
			return;
		}
		//alert(id);
		//window.location.href = "${ctx}/type/update.do?id=" + id;
		$('#myModal').modal({
		  keyboard: false,
		  remote:"${ctx}/book/update.do?id=" + id
		});
	}
</script>
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>