package org.cryptobroker.jpa.repository;

import org.cryptobroker.jpa.entities.BTCAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTCAccountRepository extends JpaRepository<BTCAccount, Long> {
}
