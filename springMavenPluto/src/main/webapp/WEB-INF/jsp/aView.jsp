<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- 跳转到 edit页面的 -->
	<portlet:renderURL var="edit">
		<portlet:param name="action" value="edit" />
	</portlet:renderURL>

	<portlet:resourceURL id="firstAjaxDemo" var="firstAjaxDemo"></portlet:resourceURL>

	<p>${welcome_message}</p>

	<div>
		跳转到编辑页面to edit page<a href="${edit}">编辑</a>
	</div>
	<div>
		跳转到百度页面to baidu page<a href="http://www.baidu.com">编辑</a>
	</div>
	<input id="ajaxInput" type="text" name="try an ajax request">
	<button  id="ajaxSubmit">try!</button>
	<br/>
	

	<div id="message"></div>
</body>

<script type="text/javascript">
	var rentalsUserListURL = '<%=firstAjaxDemo%>';
	var namespace = '<portlet:namespace/>';
	function doAjaxRequest(ajax_message) {
		$.ajax({
			url : rentalsUserListURL,
			type : "post",
			dataType:"json",
			data:{
				ajax_message: ajax_message
			},
			success:function(result) {
				alert(JSON.stringify(result));
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest, textStatus, errorThrown);
				console.info("测试失败: ");
			}
		});
	}
	
	$("#ajaxSubmit").click(function(){
		var ajax_message=$("#ajaxInput").val();
		alert(ajax_message);
		doAjaxRequest(ajax_message);
	});
</script>
</html>