package com.javatechie.smartparking.events;

import java.time.LocalDateTime;

public record VehicleEnteredEvent(String vehicleNumber, String slotCode, LocalDateTime entryTime) {
}
