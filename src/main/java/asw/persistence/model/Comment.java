package asw.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TComments")
public class Comment extends Votable{

	private String content;	

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Proposal proposal;

	public Comment() {
	}	
	
	public Comment(String content, User user, Proposal proposal) {
		super();
		setContent(content);
		Association.MakeComment.link(user, this, proposal);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public User getParticipant() {
		return user;
	}

	public void setParticipant(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	void _setUser(User user) {
		this.user = user;
	}

	public Proposal getProposal() {
		return proposal;
	}	

	void _setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((proposal == null) ? 0 : proposal.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (proposal == null) {
			if (other.proposal != null)
				return false;
		} else if (!proposal.equals(other.proposal))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
