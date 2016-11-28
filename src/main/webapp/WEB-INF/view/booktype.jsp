<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="base/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增图书类型</title>
<script type="text/javascript" src="${ctx }/static/jquery/dist/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/static/bootstrap/dist/js/bootstrap.min.js" charset="utf-8"></script>
<link rel="Stylesheet" type="text/css" href="${ctx }/static/bootstrap/dist/css/bootstrap.min.css" />
<script type="text/javascript">
	function save(){
		var name=$('#name').val()
		var pid = $('#pid').val()
		$.post('${ctx}/type/save.do',{name:name,pid:pid},function(data){
			alert(data.code);
		});
	}
</script>
</head>
<body>
	<form id="btype">
		<fieldset class="form-group">
			<label for="name">类型名称</label>
			<input type="text" class="form-control" id="name" placeholder="图书类型名称">
		</fieldset>
		<fieldset class="form-group">
			<label for="pid">父级编码</label>
			<input type="text" class="form-control" id="pid" placeholder="父级编码">
		</fieldset>
		<input id="btnSave" type="button" onclick="save()">
	</form>
</body>
</html>