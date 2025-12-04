package com.example.vh.repository;


import com.example.vh.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Vehicle> findAll() {
        return mongoTemplate.findAll(Vehicle.class);
    }

    public Vehicle findById(String id) {
        return mongoTemplate.findById(id, Vehicle.class);
    }

    public Vehicle save(Vehicle vehicle) {
        return mongoTemplate.save(vehicle);
    }

    public boolean deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Vehicle.class).getDeletedCount() > 0;
    }

    public List<Vehicle> findByBrand(String brand) {
        Query query = Query.query(Criteria.where("brand").is(brand));
        return  mongoTemplate.find(query, Vehicle.class);
    }

}
