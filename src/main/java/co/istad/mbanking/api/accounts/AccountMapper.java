package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountType;
import co.istad.mbanking.api.accounttype.AccountTypeProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {
    @InsertProvider(type = AccountProvider.class, method = "buildInsertAccountSql")
    @Result(column = "account_type",property = "accountType",one=@One(select = "getAccountTypeId"))
    void inertAccountNew(@Param("u") Accounts accounts);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectAccountSql")
    @Results(id = "accountResult", value = {
            @Result(column = "account_type", property = "accountType", one = @One(select = "getAccountTypeId"))
    })
    List<Accounts> select();

    @Select("SELECT * FROM account_type WHERE id = #{account_type}")
    AccountType getAccountTypeId(Integer accountTypeId);


}
