package com.example.vh.controller.v1;


import com.example.vh.models.Owner;
import com.example.vh.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getOwners(){
        return ownerService.getOwners();
    }


    @PostMapping
    public Owner saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable String id){
        return ownerService.getOwnerById(id);
    }
}
