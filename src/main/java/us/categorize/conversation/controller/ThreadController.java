package us.categorize.conversation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.categorize.conversation.model.Post;
import us.categorize.conversation.service.PostService;

@RestController 
public class ThreadController {

	private PostService service = new PostService();
	
	@RequestMapping("/thread")
	public Post thread(@RequestParam(value="thread") String threadId){
		Post post = service.get(threadId);
		System.out.println("Post " + post);
		return post;
	}
}
