////http://developer.51cto.com/art/201305/392668.htm

window.require.config({
    //baseUrl: '',
    paths: {
       'underscore':'../tool/underscore',
       'backbone':'../frame/backbone'
    },
    shim: {
        'backbone': {
            deps: ['underscore'],
            exports:'Backbone'
        }
    }
}
);

//define
require([
        'backbone'
       ],
       function(Backbone){
           console.log("welcome to require js.");
       }
);