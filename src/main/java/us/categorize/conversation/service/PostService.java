package us.categorize.conversation.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.categorize.conversation.dao.UserDao;
import us.categorize.conversation.model.Post;
import us.categorize.conversation.model.PostRepository;

@Service
public class PostService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PostRepository postRepository;
    	
	public PostService(){
		
	}
	
	public List<Post> byTag(String... tags){
		System.out.println("Searching with " + Arrays.toString(tags));
		if(tags.length>=2){
			return postRepository.findBy2Tags(tags[0], tags[1]);
		}
		return  postRepository.findBy1Tag(tags[0]);
	}
	
	public Post get(long id){
		return postRepository.findOne(id);
	}
	
	public void set(Post thread){
		postRepository.save(thread);	
	}
	
}
