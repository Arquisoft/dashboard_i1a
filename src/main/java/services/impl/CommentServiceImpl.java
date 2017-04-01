package services.impl;

import java.util.List;

import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;
import persistence.repositories.CommentRepository;
import services.CommentService;

public class CommentServiceImpl implements CommentService {
	
	private CommentRepository repository;	

	@Override
	public void save(Comment comment) {
		repository.save(comment);

	}

	@Override
	public List<Comment> findAll() {
		return (List<Comment>) repository.findAll();
	}

	@Override
	public List<Comment> findByProposal(Proposal proposal) {
		return repository.findByProposal(proposal);
	}

	@Override
	public List<Comment> findByUser(User user) {
		return repository.findByUser(user);
	}

}
