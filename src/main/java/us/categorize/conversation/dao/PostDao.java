package us.categorize.conversation.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import us.categorize.conversation.model.Post; 
import static java.lang.System.out;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PostDao {
	
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDao(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Post fetch(long id){
    	String fetchSQL = "SELECT * from posts where id = ?";
    	return jdbcTemplate.queryForObject(fetchSQL, 
    			new RowMapper<Post>(){
					@Override
					public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
						Post newPost = new Post();
						newPost.set_id(rs.getLong("id"));
						newPost.setTitle(rs.getString("title"));
						newPost.setBody(rs.getString("body"));
						newPost.setParentId(rs.getLong("parentId"));
						newPost.setThreadId(rs.getLong("threadId"));
						return newPost;
					}
    		
    			},
    			id
    			);
    	
    }
    
    public Post create(Post source)
    {
    	Long nextId = jdbcTemplate.queryForObject("select nextval('post_sequence')", Long.class);
    	out.print("We're creating a new post with " + nextId.toString());
    	source.set_id(nextId.longValue());
    	String createSQL = "INSERT into posts(id, title, body, parentId, threadId) values (?,?,?,?,?)";
    	jdbcTemplate.update(createSQL, 
    			source.get_id(),
    			source.getTitle(),
    			source.getBody(), 
    			source.getParentId(),
    			source.getThreadId()
    			);
    	
    	return source;
    }
	
}
