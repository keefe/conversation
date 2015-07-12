package us.categorize.conversation.model;

import java.util.Date;

public class Post {
	private long _id; 
	private long parentId = -1;
	private long threadId = -1; 
	
	private String id;
	private String title;
	private String body;
	private String tags[];
	private String authorId;
	private String authorName;
	private Date createdAt = new Date();
	public long get_id() {
		return _id;
	}
	public String getAuthorId() {
		return authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getBody() {
		return body;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public String getId() {
		return id;
	}
	public long getParentId() {
		return parentId;
	}
	public String[] getTags() {
		return tags;
	}
	public long getThreadId() {
		return threadId;
	}
	public String getTitle() {
		return title;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}
	public void setTitle(String title) {
		this.title = title;
	} 

}
