package us.categorize.conversation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import us.categorize.conversation.dao.PostDao;
import us.categorize.conversation.dao.UserDao;
import us.categorize.conversation.model.Post;

@Service
public class PostService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PostDao postDao;
    
	private Map<String, Post> threads = new HashMap<>();
	
	public PostService(){
		
	}
	
	public PostService(UserDao userDao, PostDao postDao){
		this.userDao = userDao;
		this.postDao = postDao;
	}
	
	public Post get(long id){
		return postDao.fetch(id);
	}
	
	public void set(Post thread){
		Post setPost = postDao.create(thread);		
	}
	
}
