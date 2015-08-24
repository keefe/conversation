package us.categorize.conversation.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1)", nativeQuery=true)
	List<Post> findByTags(String tag1);

	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1) AND (tag1=?2 or tag2=?2 or tag3=?2 or tag4=?2 or tag5=?2)", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2);
	
	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1) AND (tag1=?2 or tag2=?2 or tag3=?2 or tag4=?2 or tag5=?2) AND (tag1=?3 or tag2=?3 or tag3=?3 or tag4=?3 or tag5=?3)", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3);
	
	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1) AND (tag1=?2 or tag2=?2 or tag3=?2 or tag4=?2 or tag5=?2) AND (tag1=?3 or tag2=?3 or tag3=?3 or tag4=?3 or tag5=?3) AND (tag1=?4 or tag2=?4 or tag3=?4 or tag4=?4 or tag5=?4)", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3, String tag4);

	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1) AND (tag1=?2 or tag2=?2 or tag3=?2 or tag4=?2 or tag5=?2) AND (tag1=?3 or tag2=?3 or tag3=?3 or tag4=?3 or tag5=?3) AND (tag1=?4 or tag2=?4 or tag3=?4 or tag4=?4 or tag5=?4) AND (tag1=?5 or tag2=?5 or tag3=?5 or tag4=?5 or tag5=?5)", nativeQuery=true)
	List<Post> findByTags(String tag1, String tag2, String tag3, String tag4, String tag5);
	
	@Query(value="SELECT * from posts where (threadid=?1)", nativeQuery = true)
	List<Post> findThread(long threadId);
	
	@Query(value="SELECT * from posts where parentid is null order by createdAt desc limit 100;", nativeQuery=true)
	List<Post> findTopLevel();
}
