package in.chahat.banking.repository;

import in.chahat.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
//here account taken for table use and long is the type of primary id of this table
public interface AccountRepository extends JpaRepository<Account, Long> {

}
