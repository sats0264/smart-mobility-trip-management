package com.smartmobilitytripmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripCompletedEvent {
    private Long tripId;
    private Long userId;
    private String transportType;
    private LocalDateTime startTime;
    private String startLocation;
    private LocalDateTime endTime;
    private String endLocation;
    private String status;
}
