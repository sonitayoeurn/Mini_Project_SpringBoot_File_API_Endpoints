package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private static final String tableNameAccount = "account_types";
    public String buildSelectSql(){
        return new SQL(){{
            // TODO:
            SELECT ("*");
            FROM("account_types");
        }}.toString();
    }

    //    C = Create New Account Type
    public String buildInsertAccountTypeSql(){
        return new SQL(){{
            INSERT_INTO(tableNameAccount);
            VALUES("name", "#{u.name}");
        }}.toString();
    }

    //    R = Retrieved Account type (Select account type by Id)
    public String buildSelectAccountTypeByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM( tableNameAccount);
            WHERE("id = #{id}");
        }}.toString();
    }


    //    U = Update Account type by id

    public String buildUpdateAccountTypeByIdSql(){
        return new SQL(){{
            UPDATE(tableNameAccount);
            SET("name = #{a.name}");
            WHERE("id = #{a.id}");
        }}.toString();
    }

    //    D = Delete Account type by Id
    public String buildDeleteAccountTypeByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableNameAccount);
            WHERE("id = #{id}");
        }}.toString();
    }

}
