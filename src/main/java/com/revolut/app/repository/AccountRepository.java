package com.revolut.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revolut.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    List<Account> findAll();

}
