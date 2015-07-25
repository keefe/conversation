package us.categorize.conversation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.categorize.conversation.dao.PostDao;
import us.categorize.conversation.dao.UserDao;
import us.categorize.conversation.model.Post;
import us.categorize.conversation.model.PostRepository;

@Service
public class PostService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PostRepository postRepository;
    
	private Map<String, Post> threads = new HashMap<>();
	
	public PostService(){
		
	}
	
	public Post get(long id){
		return postRepository.findOne(id);
	}
	
	public void set(Post thread){
		postRepository.save(thread);	
	}
	
}
