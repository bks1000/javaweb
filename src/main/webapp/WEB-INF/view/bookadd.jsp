<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增图书</title>
<%@include file="base/jscss.jsp"%>
</head>
<body>
	<div>
		<form id="btype" class="form-horizontal" enctype="multipart/form-data" action="${ctx }/book/save.do" method="post">
			<div class="control-group">
				<label class="control-label" for="bookname">图书名称</label>
				<div class="controls">
					<input type="text" class="form-control" id="bookname"
						placeholder="图书名称" value="${bt.bookname }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="typeid">图书类型</label>
				<div class="controls">
					<input type="text" class="form-control" id="typeid"
						placeholder="图书类型" value="${bt.typeid }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="author">作者</label>
				<div class="controls">
					<input type="text" class="form-control" id="author" placeholder="作者"
						value="${bt.author }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="oldname">原名</label>
				<div class="controls">
					<input type="text" class="form-control" id="oldname" placeholder="原名"
						value="${bt.oldname }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="translator">译者</label>
				<div class="controls">
					<input type="text" class="form-control" id="translator" placeholder="译者"
						value="${bt.translator }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="publishing">出版日期</label>
				<div class="controls">
					<input type="text" class="form-control" id="publishing" placeholder="出版日期"
						value="${bt.publishing }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="papers">页数</label>
				<div class="controls">
					<input type="text" class="form-control" id="papers" placeholder="页数"
						value="${bt.papers }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="money">单价</label>
				<div class="controls">
					<input type="text" class="form-control" id="money" placeholder="单价"
						value="${bt.money }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isbn">ISBN</label>
				<div class="controls">
					<input type="text" class="form-control" id="isbn" placeholder="ISBN"
						value="${bt.isbn }">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="hidden" id="bookid" value="${bt.bookid }" /> <input
						id="btnSave" type="submit" class="btn btn-primary"
					    value="保存" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>