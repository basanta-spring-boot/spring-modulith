package com.javatechie.smartparking.allocation;

import java.util.Optional;

// entry module
public interface SlotAllocationPort {
    Optional<Slot> getNextAvailableSlot();
}
