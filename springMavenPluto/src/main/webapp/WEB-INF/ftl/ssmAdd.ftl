 <#assign portlet=JspTaglibs["/WEB-INF/tld/portlet_2_0.tld"]>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>

<body>
<@portlet.renderURL var="mbIndexUrl"/>
<@portlet.resourceURL id="mbAdd" var="mbAddUrl"/>

freeMaker ssmAdd 哈哈  mobileBook add
	<form id="form1" onsubmit="return false"  action="##" method="post">
  		<p>name: <input type="text" name="name" /></p>
  		<p>number: <input type="text" name="number" /></p>
  		<p><input type="button" value="Submit"  onclick="doAjaxRequest()"/>
  		<input type="button" value="Home"  onclick="location.href='${mbIndexUrl}'"/>
  		</p>
	</form>
	
	
</body>

<script type="text/javascript">
	var rentalsUserListURL = '${mbAddUrl}';
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