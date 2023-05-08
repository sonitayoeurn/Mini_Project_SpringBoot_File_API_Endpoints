package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.user.User;
import co.istad.mbanking.api.user.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertAccountTypeSql")  //C
    void inertAccountTypeNew(@Param("u") AccountType accountType);
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectAccountTypeByIdSql")  //R
    Optional<AccountType> selectAccountTypeById(@Param("id") Integer id);

    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateAccountTypeByIdSql")  //U
    void updateAccountTypeById(@Param("a") AccountType accountType);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteAccountTypeByIdSql")  //D
    void deleteAccountTypeById(@Param("id") Integer id);

    @Select("SELECT EXISTS(SELECT * FROM account_types WHERE id = #{id})")
    boolean isAccountTypeExist(@Param("id") Integer id);



}
