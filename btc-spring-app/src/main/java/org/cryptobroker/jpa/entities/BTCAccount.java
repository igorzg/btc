package org.cryptobroker.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "BTCAccount")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BTCAccount {

  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Column(name = "usd_balance")
  @JsonProperty(value = "usd_balance")
  private Double usdBalance;

  @Column(name = "btc_balance")
  @JsonProperty(value = "btc_balance")
  private Double btcBalance;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getUsdBalance() {
    return usdBalance;
  }

  public void setUsdBalance(Double usdBalance) {
    this.usdBalance = usdBalance;
  }

  public Double getBtcBalance() {
    return btcBalance;
  }

  public void setBtcBalance(Double btcBalance) {
    this.btcBalance = btcBalance;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof BTCAccount)) {
      return false;
    }

    final BTCAccount order = (BTCAccount) object;

    return this.getBtcBalance().equals(order.getBtcBalance()) &&
      this.getUsdBalance().equals(order.getUsdBalance()) &&
      this.getName().equals(order.getName()) &&
      this.getId().equals(order.getId());
  }
}
