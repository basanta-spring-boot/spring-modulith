package com.javatechie.smartparking.notification;

import com.javatechie.smartparking.entry.VehicleEnteredEvent;
import com.javatechie.smartparking.entry.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @EventListener
    public void onEntry(VehicleEnteredEvent event) {
        System.out.println("📢 Notification: Vehicle " + event.vehicleNumber() + " entered at " + event.entryTime());
    }

    @EventListener
    public void onExit(VehicleExitedEvent event) {
        System.out.println("📢 Notification: Vehicle " + event.vehicleNumber() + " exited at " + event.exitTime());
    }
}
