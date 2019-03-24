package org.cryptobroker.controllers;


import org.cryptobroker.jpa.entities.BTCAccount;
import org.cryptobroker.jpa.entities.BTCOrder;
import org.cryptobroker.jpa.service.BTCAccountService;
import org.cryptobroker.jpa.service.BTCOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BTCController {

  private final BTCAccountService btcAccountService;

  private final BTCOrdersService btcOrdersService;

  @Autowired
  public BTCController(BTCAccountService btcAccountService, BTCOrdersService btcOrdersService) {
    this.btcAccountService = btcAccountService;
    this.btcOrdersService = btcOrdersService;
  }

  @GetMapping("/accounts")
  public List<BTCAccount> findAllBTCAccounts() {
    return btcAccountService.findAll();
  }

  @PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
  public BTCAccount createAccount(@RequestBody BTCAccount btcAccount) {
    return btcAccountService.create(btcAccount);
  }

  @GetMapping("/accounts/{id}")
  public Optional<BTCAccount> fetchAccountDetails(@PathVariable Long id) {
    return btcAccountService.findById(id);
  }

  @GetMapping("/orders")
  public List<BTCOrder> findAllBTCOrders() {
    return btcOrdersService.findAll();
  }

  @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
  public BTCOrder createOrder(@RequestBody BTCOrder btcOrder) {
    return btcOrdersService.create(btcOrder);
  }

  @GetMapping("/orders/{id}")
  public Optional<BTCOrder> fetchOrderDetails(@PathVariable Long id) {
    return btcOrdersService.findById(id);
  }

}
