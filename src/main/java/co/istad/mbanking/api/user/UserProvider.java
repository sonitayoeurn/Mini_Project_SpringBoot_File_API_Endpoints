package co.istad.mbanking.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String tableName = "users";
    public String buildUserSql(@Param("u") User user){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");
            VALUES("student_card_id", "#{u.studentCardId}");
            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted", "FALSE");
        }}.toString();
    }

    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}", "is_deleted = FALSE");
        }}.toString();
    }

    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildUpdateIsDeletedByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted = FALSE");
            ORDER_BY("id DESC");

//            order by desc cuz need to lasted id

        }}.toString();
    }

//    search users by name insensitive
        public String buildSelectUserByNameSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name ILIKE CONCAT('%',#{name}, '%')");
        }}.toString();
    }


    //    search users by student card id
    public String buildSelectUserByStudentCardIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("student_card_id ILIKE CONCAT('%',#{studentCardId}, '%')");
        }}.toString();
    }


    public String buildUpdateById(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
        }}.toString();
    }


////    search user by name
//@Select("SELECT * FROM users WHERE name ILIKE CONCAT('%',#{name},'%')")
//@ResultMap("userResultMap")
//List<User> searchByName(@Param("name") String name);
//
//    //    search user by student id card
//    @Select("SELECT * FROM users WHERE student_card_id ILIKE CONCAT('%',#{studentCardId},'%')")
//    @ResultMap("userResultMap")
//    List<User> searchByStudentIdCard(@Param("studentCardId") String studentCardId);

}















