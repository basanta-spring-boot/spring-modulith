package com.javatechie.smartparking.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class EntryController {

    @Autowired private EntryService entryService;
    @Autowired private ExitService exitService;

    @PostMapping("/entry")
    public ResponseEntity<String> entry(@RequestParam String vehicleNumber) {
        entryService.vehicleEntry(vehicleNumber);
        return ResponseEntity.ok("Vehicle entered: " + vehicleNumber);
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exit(@RequestParam String vehicleNumber) {
        exitService.vehicleExit(vehicleNumber);
        return ResponseEntity.ok("Vehicle exited: " + vehicleNumber);
    }
}
