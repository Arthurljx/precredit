<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>"></base>

		<title>My JSP starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<script type="text/javascript">
			var path='<%=path%>';
			var pageRecorder = 30;
		</script>
		<script type="text/javascript" src="<%=path%>/loadResourcesController/loadAllJavaScript"></script>
		<script type="text/javascript" src="<%=path%>/js/system/post/post_index.js"></script>
	</head>

	<style type="text/css">
		

	</style>
</html>