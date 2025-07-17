package com.javatechie.smartparking.notification;

import com.javatechie.smartparking.events.VehicleEnteredEvent;
import com.javatechie.smartparking.events.VehicleExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void onVehicleEntered(VehicleEnteredEvent event) {
        System.out.println("🚗 Vehicle " + event.vehicleNumber() + " entered in slot " + event.slotCode());
    }

    @EventListener
    public void onVehicleExit(VehicleExitedEvent event) {
        System.out.println("🚙 Vehicle " + event.vehicleNumber() + " exited");
    }
}
