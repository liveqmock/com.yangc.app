<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.include file="/jsp/frame/head.jspf" />
<script type="text/javascript">
if (window.self != window.top) {
	window.top.location.href = "<%=basePath%>";
}
</script>
<script type="text/javascript" src="<%=js_lib%>jquery_plugin/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=js_custom%>login.js"></script>
</head>
<body>
</body>
</html>
