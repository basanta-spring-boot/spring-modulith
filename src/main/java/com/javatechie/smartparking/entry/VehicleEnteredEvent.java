package com.javatechie.smartparking.entry;

import java.time.LocalDateTime;

public record VehicleEnteredEvent(String vehicleNumber,String slotCode, LocalDateTime entryTime) {}
