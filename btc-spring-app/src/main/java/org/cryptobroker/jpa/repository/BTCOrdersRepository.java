package org.cryptobroker.jpa.repository;

import org.cryptobroker.jpa.entities.BTCOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BTCOrdersRepository extends JpaRepository<BTCOrder, Long> {
  List<BTCOrder> findAll();
}
