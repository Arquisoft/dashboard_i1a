package persistence.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long> {
    
    public Comment findByIdComment(String IdComment);

	public List<Comment> findByProposal(Proposal proposal);

	public List<Comment> findByUser(User user);

}