package com.example.vh.service;


import com.example.vh.models.Owner;
import com.example.vh.repository.OwnerRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepositry ownerRepositry;

    public List<Owner> getOwners(){
        return ownerRepositry.findAll();
    }

    public Owner getOwnerById(String id){
        return ownerRepositry.findById(id).orElse(null);
    }

    public Owner saveOwner(Owner owner){
        return ownerRepositry.save(owner);
    }
}
