<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../base/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增图书类型</title>
<%@include file="../base/jscss.jsp" %>
<script type="text/javascript">
	function save(){
		var name=$('#name').val()
		var pid = $('#pid').val()
		var id = $('#id').val()
		$.post('${ctx}/type/save.do',{id:id,name:name,pid:pid},function(data){
			alert(data.code);
		});
	}
</script>
</head>
<body>
	<div>
		<form id="btype" class="form-horizontal">
			 <div class="control-group">
				<label class="control-label" for="name">类型名称</label>
				<div class="controls">
					<input type="text" class="form-control" id="name" placeholder="图书类型名称" value="${bt.typeName }">
				</div>
			</div>
			 <div class="control-group">
				<label class="control-label" for="pid">父级编码</label>
				<div class="controls">
					<input type="text" class="form-control" id="pid" placeholder="父级编码" value="${bt.parentId }">
				</div>
			</div>
			<div class="control-group">
			    <div class="controls">
			      <input type="hidden" id="id" value="${bt.id }"/>
					<input id="btnSave" type="button" class="btn btn-primary" onclick="save()" value="保存"/>
			    </div>
			 </div>
		</form>
	</div>
</body>
</html>