define(
    [
     'jquery'
    ],
    function($){
       var ajaxUtil = {
           ajax: function(params) {
               var type = params.type || 'post',$params;
               if(type == 'post'){
                   $params = {
                       url: params.url,
                       data: JSON.stringify(params.$entity || params.data),
                       type:'post',
                       contentType: 'application/json',
					   dataType:'json', 
					   cache: false,
					   success: function(data,code,req) {
							if(params.$callback){
								params.$callback(code,req,data);
							}else{
								params.success(data,code,req);
							}
					   }
                   };
               }
               
               else if(type == 'get') {
                   $params = {
						url: params.url,
						data: params.data,
						type: 'get',
						dataType:'json', 
						cache: false,
						success: function(data,code,req) {
							if(params.$callback){
								params.$callback(code,req,data);
							}else{
								params.success(data,code,req);
							}
						}
				   };
               }
               
               $.ajax($params);
           }
       };
       return ajaxUtil;
    }
);