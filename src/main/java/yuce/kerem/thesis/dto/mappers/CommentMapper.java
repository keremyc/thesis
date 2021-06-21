package yuce.kerem.thesis.dto.mappers;

import yuce.kerem.thesis.dto.CommentDto;
import yuce.kerem.thesis.model.Comment;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 11:03 PM
 */
public class CommentMapper {

    /**
     * For given Comment object, return the corresponding CommentDto object.
     * @param comment
     * @return
     */
    public static CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .commentText(comment.getCommentText())
                .createdAt(comment.getCreatedAt())
                .commentBy(UserMapper.userToUserDto(comment.getCommentBy()))
                .build();
    }

    /**
     * For given CommentDto object, return the corresponding Comment object.
     * @param commentDto
     * @return
     */
    public static Comment commentDtoToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .commentText(commentDto.getCommentText())
                .createdAt(commentDto.getCreatedAt())
                .commentBy(UserMapper.userDtoToUser(commentDto.getCommentBy()))
                .build();
    }
}
