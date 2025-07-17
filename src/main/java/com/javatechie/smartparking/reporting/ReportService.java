package com.javatechie.smartparking.reporting;

import com.javatechie.smartparking.billing.BillingRecord;
import com.javatechie.smartparking.billing.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final BillingRepository billingRepo;

    public ReportService(BillingRepository billingRepo) {
        this.billingRepo = billingRepo;
    }

    public void printRevenueReport() {
        List<BillingRecord> bills = billingRepo.findAll();
        double total = bills.stream().mapToDouble(BillingRecord::getAmount).sum();
        System.out.println("🔍 Daily Revenue: ₹" + total);
    }
}
