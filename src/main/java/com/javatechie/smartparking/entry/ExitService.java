package com.javatechie.smartparking.entry;

import com.javatechie.smartparking.events.VehicleExitedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExitService {

    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;

    public ExitService(ParkingEntryRepository repository,
                       ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void vehicleExit(String vehicleNumber) {
        ParkingEntry entry = repository.findByVehicleNumberAndActiveTrue(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("🚫 No active entry found for vehicle " + vehicleNumber));

        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);
        repository.save(entry);

        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber,entry.getEntryTime(), entry.getExitTime()));
    }
}
