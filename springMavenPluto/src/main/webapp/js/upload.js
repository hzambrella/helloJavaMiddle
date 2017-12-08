function imageUpload(file,url,cb,cbfail){
 		$.ajax({
			url : url,
			type : "post",
			dataType:"json",
			data:file,
		    processData: false,
		    contentType: false,
			success:function(data) {
				//alert(data.message);
				if (data.code==0){
					cb(data);
				}else{
					cbfail(data);
				}
			},
			error: function(data) {
				cbfail(data);
			}
		});
 	}