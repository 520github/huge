define(
      [
        'backbone', 
        'text!/pages/mock/template/dynamic.html',
        '/js/mock/model/DynamicModel.js'
      ],
      function(
          Backbone, 
          Template,
          DynamicModel
          ){
          var view =Backbone.View.extend({
              el:'#view',
              events: {
                  'click #mpush': 'createOrUpdatePush',
                  'click #mpush_del': 'deletePush'
              },
              initialize: function() {
                  var template = _.template(Template, this.getTemplateData());
                  console.log(template);
                  this.$el.html(template);
              },
              getTemplateData: function() {
                  var pushData ={
                       bindId:"2",
					   keys:["appId","appKey","appScrect"],
					   values:{"appId":"121313","appKey":"998928327392737","appScrect":"978738738766"},
					   isOk:false,
					   pushType:"mpush",
					   pushName:"个推",
					   buttonName:"添加"
				  };
				  return pushData;
              },
              createOrUpdatePush: function(e) {
                  var $curr = $(e.currentTarget);
                  var pushType = $curr.attr("id");
                  var bindId = $curr.attr("bindId");
                  console.log("pushType:" + pushType + ",bindId:" + bindId);
                  
                  var pushKeys = [];
                  
                  $("input[id^='"+pushType+"_']").each(function(){
                      var key = $(this).attr("name");
                      var value = $(this).attr("value");
                      console.log(key+":"+value);
                      
                      var pushKey = {};
                      pushKey.key = key;
                      pushKey.val = value;
                      
                      pushKeys.push(pushKey);
                  });
                  
                  var datas = {};
                  datas.pushKeys = pushKeys;
                  datas.channelName = pushType;
                  
                  DynamicModel.postData({
                      data:datas,
                      success:function() {
                          console.log("post data is ok.");
                      }
                  });
              },
              deletePush:function(e) {
                  var $curr = $(e.currentTarget);
                  var pushType = $curr.attr("id");
                  var bindId = $curr.attr("bindId");
                  console.log("delete pushType:" + pushType + ",bindId:" + bindId);
                  
                  var datas = {};
                  datas.pushType = pushType;
                  datas.bindId = bindId;
                  DynamicModel.deleteData({
                      data: datas,
                      success:function() {
                          console.log("delete data is ok.");
                      }
                  });
              }
          });
          
          return view;
      }
);