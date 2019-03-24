package org.cryptobroker.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "BTCOrders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BTCOrder {

  @Id
  @Column(name = "order_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "account_id")
  @JsonProperty(value = "account_id")
  private Long accountId;

  private String details;

  @Column(name = "price_limit")
  @JsonProperty(value = "price_limit")
  private Double priceLimit;

  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean processed;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public Double getPriceLimit() {
    return priceLimit;
  }

  public void setPriceLimit(Double priceLimit) {
    this.priceLimit = priceLimit;
  }

  public Boolean getProcessed() {
    return processed;
  }

  public void setProcessed(Boolean processed) {
    this.processed = processed;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof BTCOrder)) {
      return false;
    }

    final BTCOrder order = (BTCOrder) object;


    return this.getProcessed().equals(order.getProcessed()) &&
      this.getAccountId().equals(order.getAccountId()) &&
      this.getDetails().equals(order.getDetails()) &&
      this.getPriceLimit().equals(order.getPriceLimit()) &&
      this.getId().equals(order.getId());
  }
}
