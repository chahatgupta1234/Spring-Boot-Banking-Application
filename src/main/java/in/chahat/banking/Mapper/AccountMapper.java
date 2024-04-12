package in.chahat.banking.Mapper;

import in.chahat.banking.dto.AccountDto;
import in.chahat.banking.entity.Account;

//create class to convert dto to jpa
public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    //create class to convert jpa to dto
    public static AccountDto mapToAcccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }
}
