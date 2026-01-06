package com.example.rideshare.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideEventProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;

    public RideEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRideCompleted(String rideId, String userId) {
        String message =  rideId + " : " + userId ;
        kafkaTemplate.send("ride-events", message);
    }
}
