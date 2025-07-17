package com.javatechie.smartparking.billing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingRecord, Long> {}
