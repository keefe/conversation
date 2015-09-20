package us.categorize.conversation.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import us.categorize.conversation.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findByTag(String tag);
}
