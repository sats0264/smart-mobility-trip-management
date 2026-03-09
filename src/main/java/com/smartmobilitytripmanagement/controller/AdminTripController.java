package com.smartmobilitytripmanagement.controller;

import com.smartmobilitytripmanagement.beans.Trip;
import com.smartmobilitytripmanagement.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/trips")
@RequiredArgsConstructor
public class AdminTripController {

    private final TripService tripService;

    /**
     * GET /trips/admin/all
     * Retrieves all trips for admin overview.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.findAll());
    }

    /**
     * POST /trips/admin/{id}/cancel
     * Admin-side cancellation of a trip.
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Trip> cancelTrip(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.cancelTrip(id));
    }

    /**
     * GET /trips/admin/user/{userId}
     * Gets all trips for a specific user (admin view).
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Trip>> getTripsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(tripService.getUserHistory(userId));
    }
}
