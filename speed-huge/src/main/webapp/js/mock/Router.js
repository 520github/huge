define(
       [
         'backbone',
         '/js/mock/AppManager.js'
       ],
       function(Backbone, AppManager){
           var routers = {
               'pages/:module/:params':'queryPage',
               '':'goDefault'
           };
           
           var router = Backbone.Router.extend({
               routes: routers,
               queryPage: function(module, params) {
                   console.log("module:" + module);
                   AppManager.renderPage(module);
               },
               goDefault: function() {
                   console.log("go default");
               }
           });
           
           return router;
       }
);