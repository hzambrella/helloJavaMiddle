 <#assign portlet=JspTaglibs["/WEB-INF/tld/portlet_2_0.tld"]>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>mobileBook index by hz</title>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>

<body>

<@portlet.namespace />

<@portlet.renderURL id="mbAddPage" var="mbAddPage">
	<@portlet.param name="action" value="mbAdd" />
</@portlet.renderURL>

<@portlet.renderURL id="mbDetailPage" var="mbDetailPage">
	<@portlet.param name="action" value="mbDetail" />
	<@portlet.param name="id" value="0" />
</@portlet.renderURL>

<@portlet.resourceURL id="mbDelete" var="mbDelete"/>

<@portlet.actionURL var="callAction">
	<@portlet.param name="action" value="call" />
</@portlet.actionURL>

	freeMaker ssmview 哈哈  mobileBook 
	 <p>sessionId:${sessionId}</p>
	 <p>namespace:<@portlet.namespace /></p>
	<img src="/springMavenPluto/images/phone.jpg" height="100" width="100">
	
	<#list resultList as p>
	<div id="<@portlet.namespace/>+_list" style="border:1px solid #999;">
		<p id="mb_${p.id}" >${p.name}--${p.number}</p>
		 <input type="hidden" class="mbid" value="${p.id}"/>
		<button id="<@portlet.namespace />_mb_detail_${p.id}" onclick="location.href=mbDetail(this.id)">detail</button>
		<button class="<@portlet.namespace />_mbDelete" id="mb_delete_${p.id}">delete</button>
		<button id="<@portlet.namespace />_mb_call_${p.id}" onclick="location.href='{callAction}'">call</button>
		</br>
	</div>				
	</#list>
	
	<button id="add" onclick="location.href='${mbAddPage}'">add</button>
</body>
<script type="text/javascript">
var mbDeleteURL='${mbDelete}';
var mbDetailURL='${mbDetailPage}';

$(".<@portlet.namespace />_mbDelete").click(function(){
	var id=$("#"+this.id+"").parent().children(".mbid").val();
	mbDelete(id)
})

function mbDelete(id){
	if (id==1){
		alert("this data can't be delete");
		return
	}
	$.ajax({
			url : mbDeleteURL,
			type : "post",
			dataType:"json",
			data:{
				mbId:id
			},
			success:function(data) {
				alert(data.message);
				location.reload();
			},
			error: function(data) {
				console.log(data);
				alert(data.message);
			}
		});
}

function mbDetail(id){
	var id=$("#"+id+"").parent().children(".mbid").val();
	//alert(mbDetailURL)
	mbIdURL="id/"+id;
	
	var mbDetailURLNew=mbDetailURL.replace(/id\/0/,mbIdURL);
	return mbDetailURLNew;	
}
</script>

</html>