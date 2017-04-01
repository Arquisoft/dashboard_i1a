package services;

import java.util.List;

import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;

public interface CommentService {
	
	void save(Comment comment);
	List<Comment> findAll();
	List<Comment> findByProposal(Proposal proposal);
	List<Comment> findByUser(User user);

}
