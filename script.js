    $(function(){
       $('.fot').hover(function(){
           $(this).css({width:'300px'});
       },function(){
           $(this).css({width:'200px'});
       });
       
       $('.a,.b').hover(function() {
           $(this).css({width:'340px'});
       },function(){
           $(this).css({width:'250px'});
       });
        
       $('.a').hover(function() {
           $(this).css('background','lightyellow');
       },function(){
           $(this).css('background','');
       });
        
       $('.b').hover(function() {
           $(this).css('background','lightcyan');
       },function(){
           $(this).css('background','');
       }); 
        
       $('.a,.b').hover(function() {
           $('this').fadeOut();
       },function(){
           $('this').fadeIn();
       }); 
    });