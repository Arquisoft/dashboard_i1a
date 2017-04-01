package persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TComments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String idComment;
    private String content;
    private int positiveVotes;
    private int negativeVotes;

    @ManyToOne
    private User user;

    @ManyToOne
    private Proposal proposal;

    @OneToMany(mappedBy = "commentary")
    private Set<VoteComment> votesComments = new HashSet<VoteComment>();

    public Comment() {
    }

    public Comment(Long id, String idComment, String content, int positiveVotes, int negativeVotes, User user) {
	super();
	this.id = id;
	this.idComment = idComment;
	this.content = content;
	this.positiveVotes = positiveVotes;
	this.negativeVotes = negativeVotes;
	this.user = user;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIdComment() {
	return idComment;
    }

    public void setIdComment(String idComment) {
	this.idComment = idComment;
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

    public User getParticipant() {
	return user;
    }

    public void setParticipant(User user) {
	this.user = user;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((idComment == null) ? 0 : idComment.hashCode());
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
	Comment other = (Comment) obj;
	if (idComment == null) {
	    if (other.idComment != null)
		return false;
	} else if (!idComment.equals(other.idComment))
	    return false;
	return true;
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

    void _setSuggestion(Proposal proposal) {
	this.proposal = proposal;
    }

    public Set<VoteComment> getVotesComments() {
	return new HashSet<VoteComment>(votesComments);
    }

    Set<VoteComment> _getVotesComments() {
	return votesComments;
    }

    void _setProposal(Proposal proposal) {
	this.proposal = proposal;
    }

}
