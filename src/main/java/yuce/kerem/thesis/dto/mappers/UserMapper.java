package yuce.kerem.thesis.dto.mappers;

import yuce.kerem.thesis.dto.UserDto;
import yuce.kerem.thesis.model.User;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 9:32 PM
 */
public class UserMapper {


    /**
     * For given userDto, returns corresponding User object.
     * @param userDto
     * @return User
     */
    public static User userDtoToUser(UserDto userDto) {

        User user = User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .occupation(userDto.getOccupation())
                .gender(userDto.getGender())
                .eduLevel(userDto.getEduLevel())
                .build();

        return user;
    }

    /**
     * For given User object, returns corresponding UserDto object;
     * @param user
     * @return UserDto
     */
    public static UserDto userToUserDto(User user) {

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .occupation(user.getOccupation())
                .gender(user.getGender())
                .eduLevel(user.getEduLevel())
                .build();

        return userDto;
    }
}
