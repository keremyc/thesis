package yuce.kerem.thesis.services.impl;

import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.Comment;
import yuce.kerem.thesis.repositories.CommentRepository;
import yuce.kerem.thesis.services.CommentService;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:56 PM
 */

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Comment save(Comment obj) {
        return commentRepository.save(obj);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> { throw new RuntimeException("No Comment with the given id"); }
        );
    }

    @Override
    public void delete(Comment obj) {
        commentRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        commentRepository.deleteById(aLong);
    }
}
