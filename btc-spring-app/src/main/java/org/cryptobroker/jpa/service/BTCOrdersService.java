package org.cryptobroker.jpa.service;

import org.cryptobroker.jpa.entities.BTCOrder;
import org.cryptobroker.jpa.repository.BTCOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BTCOrdersService {

  private final BTCOrdersRepository btcOrdersRepository;

  @Autowired
  public BTCOrdersService(BTCOrdersRepository btcOrdersRepository) {
    this.btcOrdersRepository = btcOrdersRepository;
  }

  public List<BTCOrder> findAll() {
    return btcOrdersRepository.findAll();
  }

  public Optional<BTCOrder> findById(Long userId) {
    return btcOrdersRepository.findById(userId);
  }

  public BTCOrder create(BTCOrder btcOrder) {
    return btcOrdersRepository.save(btcOrder);
  }
}
