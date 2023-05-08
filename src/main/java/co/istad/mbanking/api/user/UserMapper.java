package co.istad.mbanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "buildUserSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);


    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);


    @UpdateProvider(type = UserProvider.class, method = "buildUpdateById")
    void updateById(@Param("u") User user);



    //    search users by id
    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "is_student", property = "isStudent"),
            @Result(column = "student_card_id", property = "studentCardId")
    })
    Optional<User> selectById(@Param("id") Integer id);


    //    search all users
    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User> select();

    @Select("SELECT EXISTS(SELECT * FROM users WHERE id = #{id})")
    boolean existsById(@Param("id") Integer id);


    //    search user by name
    @SelectProvider(type = UserProvider.class, method = "buildSelectUserByNameSql")
    @ResultMap("userResultMap")
    List<User> searchByName(@Param("name") String name);

    //    search user by student card id
    @SelectProvider(type = UserProvider.class, method = "buildSelectUserByStudentCardIdSql")
    @ResultMap("userResultMap")
    List<User> searchByStudentIdCard(@Param("studentCardId") String studentCardId);

}
























