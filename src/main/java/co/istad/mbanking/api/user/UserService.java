package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {


   UserDto createNewUser(CreateUserDto createUserDto);  //   insert user
   UserDto findUserById(Integer id);  //   select user by id

   PageInfo<UserDto> findAllUser(int page, int limit);  //   select all users

//   PageInfo<UserDto> findUserByName(int page, int limit, String name); // select user by name

   Integer deleteUserById(Integer id);  // delete user by id (delete bat mg)

   Integer updateIsDeletedStatusById( Integer id, boolean status);   // update status in database tha ban delete but keep
                                                                        //record in database the same

   UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);   // update user by id

   //    search user by name
   List<UserDto> searchUserByName(String name);
   //    search user by studentCardId
   List<UserDto> searchUserByStudentCardId(String studentCardId);
}













