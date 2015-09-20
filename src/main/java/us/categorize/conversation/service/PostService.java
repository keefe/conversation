package us.categorize.conversation.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.categorize.conversation.model.Post;
import us.categorize.conversation.model.Tag;
import us.categorize.conversation.model.UserDao;
import us.categorize.conversation.model.repository.PostRepository;
import us.categorize.conversation.model.repository.TagRepository;

@Service
public class PostService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;
    
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
	private void setupPostRelations(Post newThread){
		if(newThread.getRepliesTo()!=null && !"".equals(newThread.getRepliesTo().trim())){
			System.out.println("Deal with replies " + newThread.getRepliesTo());
			Post repliesPost = get(Long.parseLong(newThread.getRepliesTo()));
			newThread.setParent(repliesPost);
			if(repliesPost!=null){
				if(repliesPost.getThread()!=null){
					newThread.setThread(repliesPost.getThread());
				}else{
					newThread.setThread(repliesPost);
				}
			}
		}
		if(newThread.getThread()==null){
			newThread.setThread(newThread);
		}
	}
	
	//TODO break this out to a TagService?
	private Tag findCanonicalTag(String tag){
		List<Tag> tags = tagRepository.findByTag(tag);
		if(tags.size()>0) return tags.get(0);
		Tag serverTag = new Tag();
		serverTag.setTag(tag);
		//return tagRepository.save(serverTag);
		return serverTag;
	}
	
	private void setupTagRelations(Post newThread){
		if(newThread.getTagString() !=null){
			String tags[] = newThread.getTagString().split(" ");
			for(String t : tags){
				Tag canon = findCanonicalTag(t);
				System.out.println("Let's check " + t + " , " + canon.getId());
				newThread.getTags().add(canon);
			}
		}
	}
	public void set(Post thread){
		setupPostRelations(thread);
		setupTagRelations(thread);
		System.out.println(thread.toString());
		postRepository.save(thread);	
	}
	
}
