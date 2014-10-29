define([
       'underscore',
       '/js/util/ajax-utils.js'
       ],
       function(_, Ajax){
           var baseModel = {
               init: function(mapper) {
                   var model = {};
                   _.each(mapper, function(obj){
                       if(obj) {
                           model[obj.name] = function(params) {
                               var type = obj.type || 'post';
                               params.url = obj.url;
                               params.type = type;
                               Ajax.ajax(params);
                           }
                       }                      
                   });
                   
                   return model;
               }
           };
           
           return baseModel;
       }
);