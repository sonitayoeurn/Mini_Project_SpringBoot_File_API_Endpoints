package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;
    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
       log.info("User = {} ", user);
//        can create and do logic here


        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %s is not found", id)));
        return userMapStruct.userToUserDto(user) ;
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.existsById(id);
        if(isFound) {
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %s is not found", id));

    }

    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted) {
            userMapper.updateIsDeletedById(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %s is not found", id));
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if(userMapper.existsById(id)){
            User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %s is not found", id));

    }

    @Override
    public PageInfo<UserDto> findAllUser(int page, int limit) {
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(userMapper::select);
        return userMapStruct.userPageInforToUserDtoPageInfo(userPageInfo);
    }


    @Override
    public List<UserDto> searchUserByName(String name) {
        List<User> users = userMapper.searchByName(name);
        if (users.size() > 0){
            return userMapStruct.userListToUserDtoList(users);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with name %s not found", name));
    }

    @Override
    public List<UserDto> searchUserByStudentCardId(String studentCardId) {
        List<User> users = userMapper.searchByStudentIdCard(studentCardId);
        if (users.size()>0){
            return userMapStruct.userListToUserDtoList(users);
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("user with studentCardId %s not found",studentCardId));
    }


}




































