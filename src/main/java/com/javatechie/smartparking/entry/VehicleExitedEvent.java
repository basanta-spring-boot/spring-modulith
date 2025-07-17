package com.javatechie.smartparking.entry;

import java.time.LocalDateTime;

public record VehicleExitedEvent(String vehicleNumber, LocalDateTime entryTime, LocalDateTime exitTime) {}
