    $(function(){
       $('.phot').hover(function(){
           $(this).css({width:'300px'});
       },function(){
           $(this).css({width:'200px'});
       });
       $('.box1,.box2,.box3,.box4,.box5,.box6,.box7,.box8').hover(function() {
           $(this).css({width:'340px'});
       },function(){
           $(this).css({width:'250px'});
       });
        
       $('.box1,.box3,.box6,.box8').hover(function() {
           $(this).css('background','lightyellow');
       },function(){
           $(this).css('background','');
       });
        
       $('.box2,.box4,.box5,.box7').hover(function() {
           $(this).css('background','lightcyan');
       },function(){
           $(this).css('background','');
       });     
        $('.box1,.box2,.box3,.box4,.box5,.box6,.box7,.box8').hover(function() {
           $(this).children('p').show();
           $(this).children('h2').hide();
        },function(){
           $(this).children('h2').show();
           $(this).children('p').hide();
        }); 
    });