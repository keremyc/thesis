package yuce.kerem.thesis.dto.mappers;

import yuce.kerem.thesis.dto.RecommendationDto;
import yuce.kerem.thesis.model.Recommendation;
import yuce.kerem.thesis.model.WebPage;

import java.util.stream.Collectors;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 11:03 PM
 */
public class RecommendationMapper {

    /**
     * For given Recommendation object, returns corresponding RecommedationDto object;
     * @param rec Recommendation object to map
     * @return
     */
    public static RecommendationDto recToRecDto(Recommendation rec) {

        RecommendationDto recDto = RecommendationDto.builder()
                .id(rec.getId())
                .recommendationText(rec.getRecommendationText())
                .liked(rec.isLiked())
                .createdAt(rec.getCreatedAt())
                .recommendedBy(UserMapper.userToUserDto(rec.getRecommendedBy()))
                .recommendedWebPage(WebPageMapper.webPageToWebPageDto(rec.getRecommendedWebPage()))
                .comments(
                        rec.getComments().stream()
                        .map( c -> CommentMapper.commentToCommentDto(c) )
                        .collect(Collectors.toSet())
                )
                .build();

        return recDto;
    }

    /**
     * For given RecommendationDto object, returns corresponding Recommedation object;
     * @param recDto RecommendationDto object to map
     * @return
     */
    public static Recommendation recDtoToRec(RecommendationDto recDto) {

        Recommendation rec = Recommendation.builder()
                .id(recDto.getId())
                .recommendationText(recDto.getRecommendationText())
                .liked(recDto.isLiked())
                .createdAt(recDto.getCreatedAt())
                .recommendedBy(UserMapper.userDtoToUser(recDto.getRecommendedBy()))
                .recommendedWebPage(WebPageMapper.webPageDtoToWebPage(recDto.getRecommendedWebPage()))
                .comments(
                        recDto.getComments().stream()
                        .map( c -> CommentMapper.commentDtoToComment(c) )
                        .collect(Collectors.toSet())
                )
                .build();

        return rec;
    }
}
