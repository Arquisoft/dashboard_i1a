package persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findByProposal(Proposal proposal);

	public List<Comment> findByUser(User user);

}