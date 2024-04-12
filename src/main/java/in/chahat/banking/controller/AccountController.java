package in.chahat.banking.controller;

import in.chahat.banking.dto.AccountDto;
import in.chahat.banking.service.AccountService;
import jakarta.persistence.Id;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;


@RestController
//RestController combines controller and response body which is capable of handling http requests and return response body Spring Boot automatically converts that object to the appropriate format in json or xml
@RequestMapping("api/accounts") //This is base url of all Rest api that we define in accountController class
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account REST API
    //1st create a method
    //2nd make that method as a REST API by using spring annotation

    @PostMapping
    //requestBody converts json into java object
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //get account REST API
    @GetMapping("/{id}")
    //@pathVariable is used to connect the upcoming url id to this below id|
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit REST Api
    @PutMapping("/{id}/deposit")
    //@pathVariable is used to connect the upcoming url id to this below id|
    //RequestBody is used to connect or map the coming json body to this Map java object.
    public ResponseEntity<AccountDto> deposit(@PathVariable long id,@RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw REST Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get all accounts REST Api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete Account Api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully of id: "+ id);
    }

}
