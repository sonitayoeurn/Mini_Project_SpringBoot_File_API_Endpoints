package co.istad.mbanking.api.user.web;

import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(name = "limit", required = false, defaultValue = "20") int limit){
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUser(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been found successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDtoPageInfo)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserDto updateUserDto){
        UserDto userDto = userService.updateUserById(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody isDeletedDto dto) {
        Integer deletedId = userService.updateIsDeletedStatusById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }


    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        Integer deletedId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been created successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())  //200
                .message("User has been Found")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    //    search user by name
    @GetMapping("/search-by-name")
    public BaseRest<?> searchUserByName(@RequestParam(name = "name") String name) {
        List<UserDto> userDtoList = userService.searchUserByName(name);
        return BaseRest.builder().status(true).code(HttpStatus.OK.value()).message("User have been found").timestamp(LocalDateTime.now())
                .data(userDtoList).build();
    }
    @GetMapping("/search-by-studentCardId")
    public BaseRest<?> searchByStudentCardId(@RequestParam("studentCardId") String studentCardId){
        List<UserDto> userDtos = userService.searchUserByStudentCardId(studentCardId);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("user have been found")
                .timestamp(LocalDateTime.now())
                .data(userDtos)
                .build();
    }


}






















