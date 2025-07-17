package com.javatechie.smartparking.entry;

import com.javatechie.smartparking.allocation.Slot;
import com.javatechie.smartparking.allocation.SlotAllocationPort;
import com.javatechie.smartparking.events.VehicleEnteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EntryService {

    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;
    private final SlotAllocationPort allocator; // decoupled dependency

    public EntryService(ParkingEntryRepository repository,
                        ApplicationEventPublisher publisher,
                        SlotAllocationPort allocator) {
        this.repository = repository;
        this.publisher = publisher;
        this.allocator = allocator;
    }

    public void vehicleEntry(String vehicleNumber) {

        Optional<Slot> slotOptional = allocator.getNextAvailableSlot(); // or findFirstByAvailableTrue

        if (slotOptional.isEmpty()) {
            throw new RuntimeException("🚫 No available slots!");
        }

        Slot slot = slotOptional.get();

        ParkingEntry entry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repository.save(entry);

        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, slot.getSlotCode(), entry.getEntryTime()));
    }
}
