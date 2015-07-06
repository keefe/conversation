package us.categorize.conversation.service;

import java.util.HashMap;
import java.util.Map;

import us.categorize.conversation.model.Post;

public class PostService {

	private Map<String, Post> threads = new HashMap<>();
	
	public PostService(){
		Post post = new Post();
		post.setId("test");
		post.setBody("This is a test body");
		post.setTags(new String[]{"tag1", "tag2", "tag3"});
		set(post);
	}
	
	public Post get(String id){
		return threads.get(id);
	}
	
	public void set(Post thread){
		threads.put(thread.getId(), thread);
	}
	
}
