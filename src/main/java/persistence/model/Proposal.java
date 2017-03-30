package persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TProposals")
public class Proposal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String idProposal;
	private String title;
	private String content;
	private int positiveVotes;
	private int negativeVotes;
	private int minVotes;
	
	@OneToMany(mappedBy = "proposal")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(mappedBy = "proposal")
	private Set<VoteProposal> votesProposal = new HashSet<VoteProposal>();
	
	@ManyToOne
	private User user;	
	
	public Proposal(){}	
	
	public Proposal(Long id, String idProposal, String title, String content, int positiveVotes, int negativeVotes,
			int minVotes) {
		super();
		this.id = id;
		this.idProposal = idProposal;
		this.title = title;
		this.content = content;
		this.positiveVotes = positiveVotes;
		this.negativeVotes = negativeVotes;
		this.minVotes = minVotes;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdProposal() {
		return idProposal;
	}
	public void setIdProposal(String idProposal) {
		this.idProposal = idProposal;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPositiveVotes() {
		return positiveVotes;
	}
	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}
	public int getNegativeVotes() {
		return negativeVotes;
	}
	public void setNegativeVotes(int negativeVotes) {
		this.negativeVotes = negativeVotes;
	}
	public int getMinVotes() {
		return minVotes;
	}
	public void setMinVotes(int minVotes) {
		this.minVotes = minVotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProposal == null) ? 0 : idProposal.hashCode());
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
		Proposal other = (Proposal) obj;
		if (idProposal == null) {
			if (other.idProposal != null)
				return false;
		} else if (!idProposal.equals(other.idProposal))
			return false;
		return true;
	}
	
	public Set<Comment> getComments(){
		return new HashSet<Comment>(comments);
	}
	
	Set<Comment> _getComments(){
		return this.comments;
	}

	public void setUser(User user) {
		this.user=user;		
	}

	public Set<VoteProposal> getVotesProposal() {
		return new HashSet<VoteProposal>(votesProposal);
	}	
	
	Set<VoteProposal> _getVotesProposal(){
		return votesProposal;
	}
}