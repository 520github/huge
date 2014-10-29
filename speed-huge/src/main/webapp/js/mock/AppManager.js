define(
       [
       '/js/mock/view/TestView.js',
       '/js/mock/view/DynamicView.js'
       ],
       function(
           TestView,
           DynamicView
       ){
           var appManager = {
               renderPage: function(page) {
                   switch(page) {
                       case 'test':
                           console.log("go to testView");
                           new TestView;
                           break;
                       case 'dynamic':
                           console.log("go to dynamicView");
                           new DynamicView;
                           break;
                   }
               }
           };
           
           return appManager;
       }
)