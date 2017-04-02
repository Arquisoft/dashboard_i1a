package persistence.model;

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
	private User User;
	
	@Id @ManyToOne
	private Votable votable;
	
	private VoteType voteType;

	private boolean value;

	Vote() {}

	public Vote(User User, Votable votable, boolean value) {
		this.value = value;
		Association.Votation.link(User, this, votable);
	}	

	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public void _setUser(User User) {
		this.User = User;
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

}
