 <#assign portlet=JspTaglibs["/WEB-INF/tld/portlet_2_0.tld"]>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>

<body>
<@portlet.renderURL var="mbIndexUrl"/>
<@portlet.resourceURL id="mbEdit" var="mbEdit"/>

freeMaker ssmAdd 哈哈  mobileBook add ${sessionId}
	<form id="form1" onsubmit="return false"  action="##" method="post">
	 <input type="hidden" type="text" name="mbId" value=${mbId} />
  		<p>name: <input type="text" name="name" value=${name} /></p>
  		<p>number: <input type="text" name="number" value=${number} /></p>
  		<p><input type="button" value="Edit"  onclick="doAjaxRequest()"/>
  		<input type="button" value="Home"  onclick="location.href='${mbIndexUrl}'"/></p>
	</form>
	
	
</body>

<script type="text/javascript">
	var rentalsUserListURL = '${mbEdit}';
	var indexUrl='${mbIndexUrl}';
	function doAjaxRequest() {
		$.ajax({
			url : rentalsUserListURL,
			type : "post",
			dataType:"json",
			data:$('#form1').serialize(),
			success:function(data) {
				alert(data.message);
				location.href=indexUrl;
			},
			error: function(data) {
				console.log(data);
				if (undifined==data.message){
					data.message='serve error';
				}
				alert(data.message);
			}
		});
	}
</script>

</html>