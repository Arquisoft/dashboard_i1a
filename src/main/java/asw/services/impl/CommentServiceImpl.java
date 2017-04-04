package asw.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.persistence.model.Comment;
import asw.persistence.model.Proposal;
import asw.persistence.model.User;
import asw.persistence.repositories.CommentRepository;
import asw.services.CommentService;

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

	@Override
	public void delete(Comment comment) {
		repository.delete(comment);		
	}

}
