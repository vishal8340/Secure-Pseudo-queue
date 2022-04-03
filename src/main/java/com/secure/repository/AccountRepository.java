package com.secure.repository;


import com.secure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountHolderName(String accountHolderName);
    Optional<Account> findByAccountNumber(int accountNumber);
    boolean findByStatus(boolean status);
}
