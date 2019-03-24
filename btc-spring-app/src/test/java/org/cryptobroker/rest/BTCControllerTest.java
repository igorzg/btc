package org.cryptobroker.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cryptobroker.controllers.BTCController;
import org.cryptobroker.jpa.entities.BTCAccount;
import org.cryptobroker.jpa.entities.BTCOrder;
import org.cryptobroker.jpa.service.BTCAccountService;
import org.cryptobroker.jpa.service.BTCOrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = BTCController.class)
@RunWith(SpringRunner.class)
public class BTCControllerTest {

  private static Logger logger = LoggerFactory.getLogger(BTCControllerTest.class);

  @Autowired
  private MockMvc mvc;

  @MockBean
  BTCOrdersService btcOrdersService;

  @MockBean
  BTCAccountService btcAccountService;

  @Autowired
  ObjectMapper objectMapper;


  @Test
  public void getOrderList() throws Exception {
    List<BTCOrder> btcOrderList = new ArrayList<>();
    BTCOrder btcOrder = new BTCOrder();
    btcOrder.setId(1L);
    btcOrder.setDetails("First order");
    btcOrder.setAccountId(1L);
    btcOrder.setPriceLimit(10.80);
    btcOrder.setProcessed(false);
    btcOrderList.add(btcOrder);

    BTCOrder btcOrder1 = new BTCOrder();
    btcOrder1.setId(2L);
    btcOrder1.setDetails("Second order");
    btcOrder1.setAccountId(1L);
    btcOrder1.setPriceLimit(20.80);
    btcOrder1.setProcessed(false);
    btcOrderList.add(btcOrder1);

    given(btcOrdersService.findAll()).willReturn(List.of(btcOrder, btcOrder1));

    String uri = "/orders";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    List<BTCOrder> btcOrderList1 = objectMapper.readValue(
      content,
      objectMapper.getTypeFactory().constructCollectionType(List.class, BTCOrder.class)
    );
    assertEquals(btcOrderList1, btcOrderList);
  }


  @Test
  public void getOrderById() throws Exception {
    BTCOrder btcOrder = new BTCOrder();
    btcOrder.setId(1L);
    btcOrder.setDetails("First order");
    btcOrder.setAccountId(1L);
    btcOrder.setPriceLimit(10.80);
    btcOrder.setProcessed(false);

    given(btcOrdersService.findById(btcOrder.getId())).willReturn(Optional.of(btcOrder));

    String uri = "/orders/1";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    BTCOrder btcOrder1 = objectMapper.readValue(
      content,
      BTCOrder.class
    );
    assertEquals(btcOrder, btcOrder1);
  }

  @Test
  public void createOrder() throws Exception {
    BTCOrder btcOrder = new BTCOrder();
    btcOrder.setId(1L);
    btcOrder.setDetails("First order");
    btcOrder.setAccountId(1L);
    btcOrder.setPriceLimit(10.80);
    btcOrder.setProcessed(false);


    given(btcOrdersService.create(btcOrder)).willReturn(btcOrder);

    String uri = "/orders";
    String json = objectMapper.writeValueAsString(btcOrder);
    MvcResult mvcResult = mvc.perform(
      MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(json)
        .accept(MediaType.APPLICATION_JSON_VALUE)
    ).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    BTCOrder btcOrder1 = objectMapper.readValue(
      content,
      BTCOrder.class
    );
    assertEquals(btcOrder, btcOrder1);
  }


  @Test
  public void getAccountList() throws Exception {
    List<BTCAccount> btcAccounts = new ArrayList<>();
    BTCAccount btcAccount = new BTCAccount();
    btcAccount.setId(1L);
    btcAccount.setBtcBalance(0.0);
    btcAccount.setUsdBalance(20.0);
    btcAccount.setName("acc_one");
    btcAccounts.add(btcAccount);

    BTCAccount btcAccount1 = new BTCAccount();
    btcAccount1.setId(2L);
    btcAccount1.setBtcBalance(0.0);
    btcAccount1.setUsdBalance(10.0);
    btcAccount1.setName("acc_two");
    btcAccounts.add(btcAccount1);

    given(btcAccountService.findAll()).willReturn(List.of(btcAccount, btcAccount1));

    String uri = "/accounts";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    List<BTCOrder> btcOrderList1 = objectMapper.readValue(
      content,
      objectMapper.getTypeFactory().constructCollectionType(List.class, BTCAccount.class)
    );
    assertEquals(btcOrderList1, btcAccounts);
  }


  @Test
  public void createAccount() throws Exception {
    BTCAccount btcAccount = new BTCAccount();
    btcAccount.setId(1L);
    btcAccount.setBtcBalance(0.0);
    btcAccount.setUsdBalance(20.0);
    btcAccount.setName("acc_one");

    String json = objectMapper.writeValueAsString(btcAccount);
    given(btcAccountService.create(btcAccount)).willReturn(btcAccount);

    String uri = "/accounts";

    MvcResult mvcResult = mvc.perform(
      MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(json)
        .accept(MediaType.APPLICATION_JSON_VALUE)
    ).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    BTCAccount btcAccount1 = objectMapper.readValue(
      content,
      BTCAccount.class
    );
    assertEquals(btcAccount, btcAccount1);
  }

  @Test
  public void getAccountById() throws Exception {
    BTCAccount btcAccount = new BTCAccount();
    btcAccount.setId(1L);
    btcAccount.setBtcBalance(0.0);
    btcAccount.setUsdBalance(20.0);
    btcAccount.setName("acc_one");

    given(btcAccountService.findById(btcAccount.getId())).willReturn(Optional.of(btcAccount));

    String uri = "/accounts/1";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();

    logger.debug("Status {}", status);
    logger.debug("Content {}", content);
    assertEquals(status, HttpStatus.OK.value());
    BTCAccount btcAccount1 = objectMapper.readValue(
      content,
      BTCAccount.class
    );
    assertEquals(btcAccount, btcAccount1);
  }
}
