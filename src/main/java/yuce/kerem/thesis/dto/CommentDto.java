package yuce.kerem.thesis.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 11:02 PM
 */

@Data
@Builder
public class CommentDto {

    private Long id;
    private String commentText;
    private Date createdAt;
    private UserDto commentBy;

}
