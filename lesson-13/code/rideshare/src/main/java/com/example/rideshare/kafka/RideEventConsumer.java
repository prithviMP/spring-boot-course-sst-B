package com.example.rideshare.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RideEventConsumer {

    @KafkaListener( topics = "ride-events", groupId = "rideshare-group")
    public void consume(String message){
        System.out.println("Hi from consume method" );
        System.out.println("Received ride event: " + message);
    }
}
