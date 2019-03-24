package org.cryptobroker.jpa.service;

import org.cryptobroker.jpa.entities.BTCAccount;
import org.cryptobroker.jpa.repository.BTCAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BTCAccountService {

  private final BTCAccountRepository accountRepository;

  @Autowired
  public BTCAccountService(BTCAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public List<BTCAccount> findAll() {
    return accountRepository.findAll();
  }

  public Optional<BTCAccount> findById(Long userId) {
    return accountRepository.findById(userId);
  }

  public BTCAccount create(BTCAccount btcAccount) {
    return accountRepository.save(btcAccount);
  }
}
