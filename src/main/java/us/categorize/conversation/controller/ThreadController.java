package us.categorize.conversation.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import us.categorize.conversation.model.Post;
import us.categorize.conversation.model.UserConnection;
import us.categorize.conversation.service.PostService;

@RestController 
public class ThreadController {

	@Autowired
	private PostService service;
	
	@RequestMapping(value="/tagged", method = RequestMethod.GET)
	public List<Post> getByTag(HttpServletRequest request, Principal currentUser, @RequestParam("tags") String tags){
		try {
			tags = UriUtils.decode(tags, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String tagArray[] = tags.split(" ");
		return service.byTag(tagArray);
	}
	
	@RequestMapping(value="/thread/{threadId}", method = RequestMethod.GET)
	public Post thread(HttpServletRequest request, Principal currentUser, @PathVariable String threadId){
		UserConnection uc = (UserConnection) request.getSession().getAttribute(InitialController.USER_CONNECTION);
        String userId = currentUser == null ? null : currentUser.getName();
        System.out.println("We're logged in as " + userId + " which is " + uc.getDisplayName());
		Post post = service.get(Long.parseLong(threadId));
		System.out.println("Post " + post);
		return post;
	}
	
	@RequestMapping(value="/thread", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Post createThread(HttpServletRequest request, Principal currentUser, @RequestBody Post newThread){
		UserConnection uc = (UserConnection) request.getSession().getAttribute(InitialController.USER_CONNECTION);
		newThread.setUser(uc);
		if(newThread.getTagString() !=null){
			String tags[] = newThread.getTagString().split(" ");
			if(tags.length>=1){
				newThread.setTag1(tags[0]);
			}
			if(tags.length>=2){
				newThread.setTag2(tags[1]);
			}
			if(tags.length>=3){
				newThread.setTag3(tags[2]);
			}
			if(tags.length>=4){
				newThread.setTag4(tags[3]);
			}
			if(tags.length>=5){
				newThread.setTag5(tags[4]);
			}
		}
		if(newThread.getRepliesTo()!=null && !"".equals(newThread.getRepliesTo().trim())){
			System.out.println("Deal with replies " + newThread.getRepliesTo());
			Post repliesPost = service.get(Long.parseLong(newThread.getRepliesTo()));
			newThread.setParent(repliesPost);
			if(repliesPost!=null){
				if(repliesPost.getThread()!=null){
					newThread.setThread(repliesPost.getThread());
				}else{
					newThread.setThread(repliesPost);
				}
			}
		}
		service.set(newThread);
		
        return newThread;
	}
}
