package persistence.model;

import javax.persistence.*;

@Entity
@Table(name="TVoteProposal")
public class VoteProposal {
	
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="ID", name="PROPOSAL_ID")
	private Proposal proposal;
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="ID", name="USER_ID")
	private User user;
	private VoteType voteType;
	
	VoteProposal(){}
	
	public VoteProposal(Proposal proposal, User user){
		setUser(user);
		setProposal(proposal);
		Association.VoteProposalUser.link(this.user, this, this.proposal);
	}

	public Proposal getProposal() {
		return this.proposal;
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
	
	void setProposal(Proposal proposal){
		this.proposal = proposal;
	}

}
