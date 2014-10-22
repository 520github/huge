define(
       [
       'backbone',
       'text!/pages/mock/template/test.html',
       'i18n!/js/i18n/nls/appi18n.js'
       ],
       function(
           Backbone,
           TestTmpl,
           i18n
       ){
          var view = Backbone.View.extend({
              el:'#view',
              initialize: function() {
                  var template = _.template(TestTmpl, i18n);
                  this.$el.html(template);
                  console.log("welcome to testView: " + template);
              }
          });
          
          return view;
       }
)