<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="base/jscss.jsp" %>
</head>
<body>
<jsp:include page="base/navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row-fluid">
			<%@include file="base/sidebar.jsp" %>
			<div class="span9" id="content">
				<div class="row-fluid">
					<jsp:include page=""></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>