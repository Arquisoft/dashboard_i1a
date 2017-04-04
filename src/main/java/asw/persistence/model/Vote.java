package asw.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(KeyVote.class)
public class Vote implements Serializable {

	private static final long serialVersionUID = -8143484614238441355L;

	@Id @ManyToOne
	private User user;
	
	@Id @ManyToOne
	private Votable votable;
	
	private VoteType voteType;	

	Vote() {}

	public Vote(User user, Votable votable, VoteType voteType) {
		setVoteType(voteType);
		Association.Votation.link(user, this, votable);
	}	
	
	public void _setUser(User user) {
		this.user = user;
	}

	void _setVotable(Votable votable) {
		this.votable = votable;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((votable == null) ? 0 : votable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (votable == null) {
			if (other.votable != null)
				return false;
		} else if (!votable.equals(other.votable))
			return false;
		return true;
	}
}
