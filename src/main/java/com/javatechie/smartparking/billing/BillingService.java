package com.javatechie.smartparking.billing;

import com.javatechie.smartparking.events.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BillingService {

    private BillingRepository repository;

    @EventListener
    public void handleExit(VehicleExitedEvent event) {
        Duration duration = Duration.between(event.entryTime(), event.exitTime());
        double amount = Math.max(20, (duration.toMinutes() / 60.0) * 50); // ₹50/hour
        BillingRecord record = new BillingRecord(null, event.vehicleNumber(), amount, LocalDateTime.now());
        repository.save(record);
        System.out.println("✅ Billed ₹" + amount + " for " + event.vehicleNumber());
    }
}
