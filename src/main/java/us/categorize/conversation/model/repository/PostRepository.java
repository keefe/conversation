package us.categorize.conversation.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import us.categorize.conversation.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

	
	@Query(value="select posts.* from posts, post_tags, tags where post_tags.postId = posts.id and post_tags.tagId = tags.id and (tag=?1) group by posts.id having count(posts.id)>=1", nativeQuery=true)
	List<Post> findByTags(String tag1);

	@Query(value="select posts.* from posts, post_tags, tags where post_tags.postId = posts.id and post_tags.tagId = tags.id and (tag=?1 or tag=?2) group by posts.id having count(posts.id)>=2", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2);
	
	@Query(value="select posts.* from posts, post_tags, tags where post_tags.postId = posts.id and post_tags.tagId = tags.id and (tag=?1 or tag=?2 or tag=?3) group by posts.id having count(posts.id)>=3", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3);
	
	@Query(value="select posts.* from posts, post_tags, tags where post_tags.postId = posts.id and post_tags.tagId = tags.id and (tag=?1 or tag=?2 or tag=?3 or tag=?4) group by posts.id having count(posts.id)>=4", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3, String tag4);

	@Query(value="select posts.* from posts, post_tags, tags where post_tags.postId = posts.id and post_tags.tagId = tags.id and (tag=?1 or tag=?2 or tag=?3 or tag=?4 or tag=?5) group by posts.id having count(posts.id)>=5", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3, String tag4, String tag5);
	
	@Query(value="SELECT * from posts where (threadid=?1)", nativeQuery = true)
	List<Post> findThread(long threadId);
	
	@Query(value="SELECT * from posts where parentid is null order by createdAt desc limit 100;", nativeQuery=true)
	List<Post> findTopLevel();
}
