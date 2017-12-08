<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>
<body>
<p>hello${message}</p>
<p><input id="file" type="file" name="file"  /></p>
<p><input type="button" value="Edit" onclick="doAjaxRequest()" /></p>
<img src="/springMVC/images/1231.png" />

<script type="text/javascript">
function doAjaxRequest(){
	var f=document.getElementById("file").files;
	var form=new FormData();
	form.append("file",f[0]);
	console.log(f[0]);
	
	$.ajax({
		url : "/springMVC/hello/fileUpload",
		type : "post",
		dataType:"json",
		data:form,
	    processData: false,
	    contentType: false,
		success:function(data) {
			alert(JSON.stringify(data));
		},
		error: function(data) {
			alert(JSON.stringify(data));
		}
	});
}
</script>
</body>
</html>