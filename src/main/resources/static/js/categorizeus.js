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
	        			success:function(postedData){
	        				console.log("We've been done with posting the newthing");
	        				console.log(JSON.stringify(postedData));
	        				$(".newThread").html(applyTemplate("newpost", {}));
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
			var templateNames = ["post", "newpost"]
			var templates = {}
			var applyTemplate = function(templateName, data){
				return templates[templateName](data);
			}
			var cancelFunction = function(event){
        		    event.preventDefault();
        		    console.log("Clicking Cancel");
        		    $(".newThread").html(applyTemplate("newpost", {}));
        		    $(".newThread").css("display","none");
        		    $("#btnPostIt").off("click");
        		}
			var postFunction = function(event){
        			$(".newThread").css("display","block");
        			$("#btnPostIt").one("click", postNewThread);
        			$("#btnCancelPostIt").click(cancelFunction);//TODO is this adding many too many handlers?
        		};
        		
			var initialize = function(){
			    var template = "No Load"
			    var i;
			    for(i=0; i<templateNames.length;i++){
			    	(function(){
			    		var name = templateNames[i];
						getTemplate(name, function(tmpl){
							templates[name] = tmpl;
							if("newpost"==name){//TODO this needs to be like a async.series or something
							    $(".newThread").html(applyTemplate("newpost", {}));							
							}
							
						});
					}());    
			    }
				
				//TODO import async or underscore and do this properly
				
				
        		$("#btnPost").click(postFunction);
        		        		
        		$("#btnLoad").click(function(event){
        			event.preventDefault();
        			var whichId = $("#txtSearch").val();
        			console.log("We'd like to load " + whichId);        			
	        		$.ajax({
	        			type:"GET",
	        			url:"/thread/"+whichId,
	        			dataType:"json",
	        			success:function(data){
	        				console.log("We've had some success then");
	        				$("#output").append(applyTemplate("post", data));
	        			}
	        		});        			
        			
        		});
        		var findId = function(event){
        		   var target = $( event.target );
        		   var classes = target.attr("class").split(" ");
        		   for(var c in classes){
        		    var clazz = classes[c];
        		   	if(clazz.startsWith("id-")){
        		   		return parseInt(clazz.replace("id-",""));
        		   	}
        		   }	
        		   return undefined;
        		}
        		
        		var replyCallback = function(event){
        			console.log("Reply For " + findId(event));
        			$("#replyField").attr("value", findId(event));
        			postFunction(event);
        		};
        		var viewCallback = function(event){
        			var target = $( event.target );
        			console.log("View For " + findId(event));
        		};        		
        		
        		$("#btnSearch").click(function(event){
        		    event.preventDefault();
        			var whichTags = $("#txtSearch").val();
        			$("#output").html("");
        			$.ajax({
	        			type:"GET",
	        			url:"/tagged?tags="+whichTags,
	        			dataType:"json",
	        			success:function(data){
	        				console.log("We've had some success then");
	        				console.log(data)
	        				var i =0;
	        				for(i=0; i<data.length;i++){
	        					$("#output").append(applyTemplate("post", data[i]));
	        					var lastChild = $('#output').children('.entry').last();
	        					lastChild.find(".button-view").click(viewCallback);
	        					lastChild.find(".button-reply").click(replyCallback);
	        				}
	        			}
	        		});
        		});

}