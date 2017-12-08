<#assign portlet=JspTaglibs[ "/WEB-INF/tld/portlet_2_0.tld"]>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<!-- 		<script src="/springMavenPluto/js/upload.js"></script> -->
<script src="/springMavenPluto/js/ajaxfileupload.js"></script>
</head>

<body>
	<@portlet.renderURL var="mbIndexUrl" /> <@portlet.resourceURL
	id="mbEdit" var="mbEdit" /> <@portlet.resourceURL id="mbHeadImgUpload"
	var="mbHeadImgUpload" /> freeMaker ssmAdd 哈哈 mobileBook add
	${sessionId}
	<input id="file" type="file" name="file"  />

	<form id="form1" onsubmit="return false" action="##" method="post">
		<input type="hidden" type="text" name="mbId" value=${mbId } />
		<p>
			name: <input type="text" name="name" value=${name } /></p>
		<p>
			number: <input type="text" name="number" value=${number } /></p>
		<p>
			<input type="button" value="Edit" onclick="doAjaxRequest()" /> <input
				type="button" value="Home" onclick="location.href='${mbIndexUrl}'" />
		</p>
	</form>


</body>

<script type="text/javascript">
	var rentalsUserListURL = '${mbEdit}';
	var indexUrl = '${mbIndexUrl}';
	var mbHeadImgUpload = '${mbHeadImgUpload}';

	function doAjaxRequest() {
	imageUpload(mbHeadImgUpload, function(result) {
			alert(JSON.stringify(result))
			$.ajax({
				url : rentalsUserListURL,
				type : "post",
				dataType : "json",
				data : $('#form1').serialize(),
				success : function(data) {
					alert(data.message);
					location.href = indexUrl;
				},
				error : function(data) {
					console.log(data);
					if (undifined == data.message) {
						data.message = 'serve error';
					}
					alert(data.message);
				}
			});
		}, function(result) {
			if (undefined == result.message) {
				alert('interna server error');
			} else {
				alert(result.message);
			}
		})
	}

	function imageUpload( url, cb, cbfail) {
		$.ajaxFileUpload({
			url : url, //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : 'file', //文件上传域的ID
			dataType : 'JSON', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				cb(data);
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				cbfail(data);
			}
		})
	}
</script>

</html>