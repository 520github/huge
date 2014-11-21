define(
       [
        '/js/mock/model/BaseModel.js'
       ],function(BaseModel) {
           var mapper = [
               {
                 name:'postData',
                 url:'/huge/dynamic/post',
                 type:'post'
               },
               {
                 name:'deleteData',
                 url:'/huge/dynamic/delete'        
               },
               {
                 name:'getUnConfigChannels',
                 url:'/huge/dynamic/getUnConfigChannels',
                 type:'get'
               }
           ];
           
           return BaseModel.init(mapper);
       }       
);