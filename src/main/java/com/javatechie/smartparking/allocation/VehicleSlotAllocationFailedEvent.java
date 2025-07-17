package com.javatechie.smartparking.allocation;

public record VehicleSlotAllocationFailedEvent(String vehicleNumber, String reason) {}