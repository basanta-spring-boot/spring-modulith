package com.javatechie.smartparking.events;

import java.time.LocalDateTime;

public record VehicleExitedEvent(String vehicleNumber, LocalDateTime entryTime,LocalDateTime exitTime) {
}
