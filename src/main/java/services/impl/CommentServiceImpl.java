package services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;
import persistence.repositories.CommentRepository;
import services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository repository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void save(Comment comment) {
		repository.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		List<Comment> comments = new ArrayList<>();
		if (repository.findAll() != null) {
			Iterator<Comment> it = repository.findAll().iterator();
			while (it.hasNext())
				comments.add(it.next());
		}
		return comments;
	}

	@Override
	public List<Comment> findByProposal(Proposal proposal) {
		return repository.findByProposal(proposal);
	}

	@Override
	public List<Comment> findByUser(User user) {
		return repository.findByUser(user);
	}

	@Override
	public boolean checkExists(Long id) {
		return repository.findOne(id) != null;
	}

}
