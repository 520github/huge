define(
    ['/js/work/requirejs/math.js'],
    function(Math){
        var handle = {
            handleSum:function(x,y) {
                return Math.add(x,y);
            },
            handleRedure:function(x,y) {
                return x-y;
            }
        };
        
        return handle;
    }
);