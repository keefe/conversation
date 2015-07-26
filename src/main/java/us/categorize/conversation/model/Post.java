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
	@JoinColumn(name="parentId", unique= true, nullable=true, insertable=true, updatable=true)
	private Post parent;
	  
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="threadId", unique= true, nullable=true, insertable=true, updatable=true)
	private Post thread;

	private String body;
	
	@OneToOne (cascade=CascadeType.REFRESH)
	@JoinColumn(name="authorId", unique= true, nullable=true, insertable=true, updatable=true)
	private UserConnection user;
	
	private Date createdAt = new Date();

	private String tag1,tag2,tag3,tag4,tag5;

	private String imageUrl;
	
	@Transient
	private String tags[];
	
	@Transient
	private String tagString;
	
	
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


	public String getTag1() {
		return tag1;
	}


	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}


	public String getTag2() {
		return tag2;
	}


	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}


	public String getTag3() {
		return tag3;
	}


	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}


	public String getTag4() {
		return tag4;
	}


	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}


	public String getTag5() {
		return tag5;
	}


	public void setTag5(String tag5) {
		this.tag5 = tag5;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String[] getTags() {
		if(tags==null){
			tags = new String[]{tag1,tag2,tag3,tag4,tag5};
		}
		return tags;
	}

	

	public void setTags(String[] tags) {
		this.tags = tags;
	}


	public String getTagString() {
		if(tagString==null){
			tagString = Arrays.toString(getTags());			
		}
		return tagString;
	}


	public void setTagString(String tagString) {

		this.tagString = tagString;
	}

	

}
