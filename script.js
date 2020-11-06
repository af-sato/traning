    $(function(){
       $('.phot').hover(function(){
           $(this).css({width:'300px'});
       },function(){
           $(this).css({width:'200px'});
       });
       
       $('.a,.b,.c,.d,.e,.f,.g,.h').hover(function() {
           $(this).css({width:'340px'});
       },function(){
           $(this).css({width:'250px'});
       });
        
       $('.a,.c,.f,.h').hover(function() {
           $(this).css('background','lightyellow');
       },function(){
           $(this).css('background','');
       });
        
       $('.b,.d,.e,.g').hover(function() {
           $(this).css('background','lightcyan');
       },function(){
           $(this).css('background','');
       }); 
        
        $('.a,.b,.c,.d,.e,.f,.g,.h').hover(function() {
           var text = $('p').html();
           $(this).text(text);
        },function(){
           var title = $('h2').html();
           $(this).text(title);
        }); 
    });