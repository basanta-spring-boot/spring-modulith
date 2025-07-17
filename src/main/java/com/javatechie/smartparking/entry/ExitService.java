package com.javatechie.smartparking.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ExitService {
    @Autowired private ParkingEntryRepository repository;
    @Autowired private ApplicationEventPublisher publisher;

    public void vehicleExit(String vehicleNumber) {
        ParkingEntry entry = repository.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("No active parking found!"));
        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);
        repository.save(entry);

        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, entry.getEntryTime(), entry.getExitTime()));
    }
}
