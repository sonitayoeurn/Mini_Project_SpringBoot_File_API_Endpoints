package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);

    User updateUserDtoToUser(UpdateUserDto updateUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

    PageInfo<UserDto> userPageInforToUserDtoPageInfo(PageInfo<User> userPageInfo);
    List<UserDto> userListToUserDtoList(List<User> userList);
}
