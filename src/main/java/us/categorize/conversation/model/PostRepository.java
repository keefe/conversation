package us.categorize.conversation.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1)", nativeQuery=true)
	List<Post> findBy1Tag(String tag1);

	@Query(value="SELECT * from posts where (tag1=?1 or tag2=?1 or tag3=?1 or tag4=?1 or tag5=?1) AND (tag1=?2 or tag2=?2 or tag3=?2 or tag4=?2 or tag5=?2)", nativeQuery=true)
	List<Post> findBy2Tags(String tag1, String tag2);
}
