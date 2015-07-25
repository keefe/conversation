 $.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

			var postNewThread = function(event){
        			event.preventDefault();
        			$(".newThread").css("display","none");
        			var newBody = $("#newPostForm").serializeObject();
        			console.log("We've found it then");
        			console.log(newBody)
        			$("#newPostForm")[0].reset();
	        		$.ajax({
	        			type:"POST",
	        			url:"/thread",
	        			contentType:"application/json",
	        			dataType:"json",
	        			data:JSON.stringify(newBody), 
	        			success:function(){
	        				console.log("Why isn't this callback being triggered?");
	        			}
	        		});        			
        			
        		};
        	var getTemplate = function(name, callback) {
 			   return $.get('/templates/'+name+'.hbrs').then(function(src) {
 			    console.log("We've found")
 			    console.log(src)
       			callback(Handlebars.compile(src));
   			 });
}
			var initialize = function(){
			    var template = "No Load"
				getTemplate("post", function(tmpl){
					template = tmpl;
					
				});
				
        		$("#btnPost").click(function(event){
        			$(".newThread").css("display","block");
        			$("#btnPostIt").one("click", postNewThread);
        		});
        		
        		$("#btnCancelPostIt").click(function(event){
        		    $(".newThread").css("display","none");
        		    $("#btnPostIt").off("click");
        		});
        		
        		$("#btnLoad").click(function(event){
        			event.preventDefault();
        			var whichId = $("#txtSearch").val();
        			console.log("We'd like to load " + whichId);
        			$("#newPostForm")[0].reset();
        			
	        		$.ajax({
	        			type:"GET",
	        			url:"/thread/"+whichId,
	        			dataType:"json",
	        			success:function(data){
	        				console.log("We've had some success then");
	        				$("#output").append(template(data));
	        			}
	        		});        			
        			
        		});

}