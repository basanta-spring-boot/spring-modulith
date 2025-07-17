package com.javatechie.smartparking.allocation;

import com.javatechie.smartparking.entry.VehicleEnteredEvent;
import com.javatechie.smartparking.entry.VehicleExitedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SlotAllocator implements SlotAllocationPort{

    @Autowired private SlotRepository slotRepo;

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
        slotRepo.findByVehicleNumber(event.vehicleNumber()).ifPresent(slot -> {
            slot.setAvailable(true);
            slot.setVehicleNumber(null);
            slotRepo.save(slot);
            System.out.println("🅿️ Slot " + slot.getSlotCode() + " freed for vehicle " + event.vehicleNumber());
        });
    }

    @Override
    public Optional<Slot> getNextAvailableSlot() {
        return slotRepo.findFirstByAvailableTrue();
    }
}
