package persistence.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VoteComment {
	
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="ID", name="COMMENT_ID")
	private Comment comment;
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="ID", name="USER_ID")
	private User user;
	private VoteType voteType;
	
	VoteComment(){}
	
	public VoteComment(Comment comment, User user){
		setUser(user);
		setComment(comment);
		Association.VoteCommentUser.link(this.user, this, this.comment);
	}

	public Comment getComment() {
		return this.comment;
	}

	public User getUser() {
		return this.user;
	}
	
	public VoteType getVoteType(){
		return this.voteType;
	}
	
	void setUser(User user){
		this.user = user;
	}
	
	void setComment(Comment comment){
		this.comment = comment;
	}

}
