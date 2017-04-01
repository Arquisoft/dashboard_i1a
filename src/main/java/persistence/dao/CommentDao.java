package persistence.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Comment;

@Transactional
public interface CommentDao extends CrudRepository<Comment, Long> {
    
    public Comment findByIdComment(String IdComment);

}