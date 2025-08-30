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
    public void ImplementDelete(int id) {
           remote.deleteById(id);
        }

    //filter Policies
    public List<Insurance> filterPolicyType(String policy_type) {
        return remote.findAllByPolicyType(policy_type);
    }

    public List<Insurance> filterPremiumAmount(double start,double end) {
        return remote.findAllByPremiumAmountGreaterThanOrPremiumAmount(start,end);
    }
}
