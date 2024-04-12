package in.chahat.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//use getter setter from lombok to automatically make getter and setter

@NoArgsConstructor
@AllArgsConstructor

@Table(name = "accounts")
@Entity
public class Account {

    //to make primary key as id we use @Id
    @Id
    //it will automatically inc id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_holder_name")
    private String  accountHolderName;
    private double balance;
}
