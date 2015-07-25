package us.categorize.conversation.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
   @SequenceGenerator(name="post_seq",
            sequenceName="post_sequence",
            allocationSize=1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE,
         generator="post_seq")
   @Column(name = "id", updatable=false)
	private long _id; 
   
	private String title;
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="parent_id", unique= true, nullable=true, insertable=true, updatable=true)
	private Post parent;
	  
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="thread_id", unique= true, nullable=true, insertable=true, updatable=true)
	private Post thread;

	private String body;
	
	//private String tags[];
	//private String authorId;
	//private String authorName;
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="author_id", unique= true, nullable=true, insertable=true, updatable=true)
	private UserConnection user;
	
	@Column(name="created_at")
	private Date createdAt = new Date();


	public long get_id() {
		return _id;
	}


	public void set_id(long _id) {
		this._id = _id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Post getParent() {
		return parent;
	}


	public void setParent(Post parent) {
		this.parent = parent;
	}


	public Post getThread() {
		return thread;
	}


	public void setThread(Post thread) {
		this.thread = thread;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public UserConnection getUser() {
		return user;
	}


	public void setUser(UserConnection user) {
		this.user = user;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	

}
