package us.categorize.conversation.model;

import java.util.Arrays;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable=false)
	private long id; 
   
	private String title;
	
	@JsonIgnore
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentId", unique= true, nullable=true, insertable=true, updatable=true)
	private Post parent;
	  
	@JsonIgnore
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="threadId", unique= true, nullable=true, insertable=true, updatable=true)
	private Post thread;

	private String body;
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="authorId", unique= true, nullable=true, insertable=true, updatable=true)
	private UserConnection user;
	
	@JsonIgnore
	private Date createdAt = new Date();

	private String imageUrl;
	
	@Transient
	private String tagString;
	
	@Transient
	private String repliesTo;
	
	private String url;
	
	private String origin;
	


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



	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTagString() {
		return tagString;
	}


	public void setTagString(String tagString) {

		this.tagString = tagString;
	}


	public String getRepliesTo() {
		return repliesTo;
	}


	public void setRepliesTo(String repliesTo) {
		this.repliesTo = repliesTo;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	

}
