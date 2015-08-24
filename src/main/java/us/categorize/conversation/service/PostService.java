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
	
	public List<Post> loadThread(String threadId){
		return postRepository.findThread(Long.parseLong(threadId));
	}
	
	public List<Post> originPosts(){
		return postRepository.findTopLevel();
	}
	
	public List<Post> byTag(String... tags){
		System.out.println("Searching with " + Arrays.toString(tags));
		if(tags.length>=5){
			return postRepository.findByTags(tags[0], tags[1], tags[2], tags[3], tags[4]);
		}else if(tags.length==4){
			return postRepository.findByTags(tags[0], tags[1], tags[2], tags[3]);
		}else if(tags.length==3){
			return postRepository.findByTags(tags[0], tags[1], tags[2]);
		}else if(tags.length==2){
			return postRepository.findByTags(tags[0], tags[1]);
		}
		List<Post> fullList =  postRepository.findByTags(tags[0]);
		return fullList;
	}
	
	public Post get(long id){
		return postRepository.findOne(id);
	}
	
	public void set(Post thread){
		System.out.println(thread.toString());
		postRepository.save(thread);	
	}
	
}
