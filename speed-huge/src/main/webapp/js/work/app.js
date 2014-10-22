////http://developer.51cto.com/art/201305/392668.htm

window.require.config({
    //baseUrl: '',
    paths: {
       'underscore':'../tool/underscore',
       'backbone':'../frame/backbone',
       'mustache':'../tool/mustache',
       //'math':'../work/requirejs/math',
       'handle':'../work/requirejs/handle',
       'text':'../tool/text',
       'i18n':'../tool/i18n'
    },
    shim: {
        'backbone': {
            deps: ['underscore'],
            exports:'Backbone'
        }
    },
    //urlArgs: 'bud=' + new Date().getTime(),
    waitSeconds: 90,
    locale: window.currentLocale || 'en_us'
});

window.currentLocale = 'en_us';

//$.ajaxSteup({cache:false});

//define
require([
        'backbone',
        '/js/mock/Router.js',
        'handle'
       ],
       function(
           Backbone,
           Router,
           Handle
        ){
           console.log("welcome to require js.");
           console.log(Handle.handleSum(1,9));
           console.log(Handle.handleRedure(9,1));
           
           var router = new Router();
           Backbone.history.start();
           
           console.log('start router');
       }
);