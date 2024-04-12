package in.chahat.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//data from lombok used for automatically generate getter and setter
@Data
@AllArgsConstructor
public class AccountDto {
    private long id;
    private String accountHolderName;
    private double balance;
}
