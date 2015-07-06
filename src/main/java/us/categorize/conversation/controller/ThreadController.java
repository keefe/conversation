package us.categorize.conversation.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import us.categorize.conversation.model.Post;
import us.categorize.conversation.model.UserConnection;
import us.categorize.conversation.service.PostService;

@RestController 
public class ThreadController {

	private PostService service = new PostService();
	
	@RequestMapping(value="/thread/{threadId}", method = RequestMethod.GET)
	public Post thread(HttpServletRequest request, Principal currentUser, @PathVariable String threadId){
		UserConnection uc = (UserConnection) request.getSession().getAttribute(InitialController.USER_CONNECTION);
        String userId = currentUser == null ? null : currentUser.getName();
        System.out.println("We're logged in as " + userId + " which is " + uc.getDisplayName());
		Post post = service.get(threadId);
		System.out.println("Post " + post);
		return post;
	}
	
	@RequestMapping(value="/thread", method = RequestMethod.POST)
	public ResponseEntity<?> createThread(HttpServletRequest request, Principal currentUser, @RequestBody Post newThread){
		newThread.setAuthorId(currentUser.getName());
		UserConnection uc = (UserConnection) request.getSession().getAttribute(InitialController.USER_CONNECTION);
		newThread.setAuthorName(uc.getDisplayName());
		service.set(newThread);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}
