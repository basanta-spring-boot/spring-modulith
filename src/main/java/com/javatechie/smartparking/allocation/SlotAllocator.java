package com.javatechie.smartparking.allocation;

import com.javatechie.smartparking.events.VehicleEnteredEvent;
import com.javatechie.smartparking.events.VehicleExitedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotAllocator implements SlotAllocationPort {

    @Autowired
    private SlotRepository slotRepo;

    @EventListener
    public void handleEntry(VehicleEnteredEvent event) {
        Slot slot = slotRepo.findBySlotCode(event.slotCode())
                .orElseThrow(() -> new RuntimeException("🚫 Slot " + event.slotCode() + " not found!"));
        slot.setAvailable(false);
        slot.setVehicleNumber(event.vehicleNumber());
        slotRepo.save(slot);

        System.out.println("🅿️ Allocated Slot " + slot.getSlotCode() + " to vehicle " + event.vehicleNumber());
    }

    @EventListener
    public void handleExit(VehicleExitedEvent event) {
        Slot slot = slotRepo.findByVehicleNumber(event.vehicleNumber())
                .orElseThrow(() -> new RuntimeException("🚫 No slot found for vehicle " + event.vehicleNumber()));
        slot.setAvailable(true);
        slot.setVehicleNumber(null);
        slotRepo.save(slot);

        System.out.println("🅿️ Freed Slot " + slot.getSlotCode() + " from vehicle " + event.vehicleNumber());
    }

    @Override
    public Optional<Slot> getNextAvailableSlot() {
        return slotRepo.findFirstByAvailableTrue();
    }
}
