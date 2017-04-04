package asw.services;

import java.util.List;

import asw.persistence.model.Comment;
import asw.persistence.model.Proposal;
import asw.persistence.model.User;

public interface CommentService {
	
	void save(Comment comment);
	boolean checkExists(Long id);
	List<Comment> findAll();
	List<Comment> findByProposal(Proposal proposal);
	List<Comment> findByUser(User user);
	void delete(Comment comment);
}
