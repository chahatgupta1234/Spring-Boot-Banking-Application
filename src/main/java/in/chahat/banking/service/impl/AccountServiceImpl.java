package in.chahat.banking.service.impl;

import in.chahat.banking.Mapper.AccountMapper;
import in.chahat.banking.dto.AccountDto;
import in.chahat.banking.entity.Account;
import in.chahat.banking.repository.AccountRepository;
import in.chahat.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//whenever spring will find any single constructor it automatically install all the dependencies of it. no need to do it by self
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    //constructor to inject this accountRepository
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    //convert dto into jpa entity then jpa into database
    public AccountDto createAccount(AccountDto accountDto) {
        //1st we convert dto data into jpa(rows and col) so that it can go in table
        Account account = AccountMapper.mapToAccount(accountDto);

        //now we save this data in table
        //it returns saved entity so we store it in saved account
        Account savedAccount = accountRepository.save(account);

        //after saving we have to again convert it in dto(data transfer object) form for further use
        return AccountMapper.mapToAcccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        //here ElseThrow demand lamda expression implementation ()->
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account hai hi nhi"));

        return AccountMapper.mapToAcccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account hai hi nhi"));

        double total = account.getBalance()+amount;
        account.setBalance(total);

        accountRepository.save(account);
        return AccountMapper.mapToAcccountDto(account);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account hai hi nhi"));

        if(account.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");

        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);


        return AccountMapper.mapToAcccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAcccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account hai hi nhi"));

        accountRepository.deleteById(id);
    }
}
