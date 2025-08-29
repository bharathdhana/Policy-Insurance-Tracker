package org.example.policytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    @Autowired
    //Creating object for Service Class
    private PolicyRemote remote;

    //Inserting Policy
    public Insurance ImplementSave(Insurance insurance) {
        return remote.save(insurance);
    }

    //Viewing Policy
    public List<Insurance> ReadEverything(){
        return remote.findAll();
    }

    //Update Policy
    public Optional<Insurance> EditElementById(int id) {
        return remote.findById(id);
    }

    //Delete Policy
    public boolean ImplementDelete(int id) {
        Optional<Insurance> insurance = remote.findById(id);
        // if policy found
        if (insurance.isPresent()) {
            remote.deleteById(id);
            return true;
        }
        // if policy not found
        else {
            return false;
        }
    }
}
