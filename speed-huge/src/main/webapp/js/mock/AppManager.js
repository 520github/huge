define(
       ['/js/mock/view/TestView.js'],
       function(
           TestView
       ){
           var appManager = {
               renderPage: function(page) {
                   switch(page) {
                       case 'test':
                           console.log("go to testView");
                           new TestView;
                           break;
                   }
               }
           };
           
           return appManager;
       }
)