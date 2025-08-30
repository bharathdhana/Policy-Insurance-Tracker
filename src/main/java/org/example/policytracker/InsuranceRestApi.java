package org.example.policytracker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// request and response in the JSON format
@RestController
@RequestMapping("/rest")
public class InsuranceRestApi {

    @Autowired
    private PolicyService service;

    // read all
    @GetMapping("/view")
    public List<Insurance> readAllPolicies() {
        return service.ReadEverything();
    }

    @PostMapping("/open")
    public Insurance openPolicies(@Valid @RequestBody Insurance insurance) {
        Insurance receive = service.ImplementSave(insurance);
        return receive;
    }

    @GetMapping("/edit/{key}")
    public Optional<Insurance> readOnePolicy(@PathVariable int key) {
        Optional<Insurance> receive = service.EditElementById(key);
        return receive;
    }

    @PutMapping("/update")
    public Insurance updatePolicies(@Valid @RequestBody Insurance insurance) {
        Insurance receive = service.ImplementSave(insurance);
        return receive;
    }

    @DeleteMapping("/remove/{key}")
    public void removeOnePolicy(@PathVariable int key) {
        service.ImplementDelete(key);
    }

    @PostMapping("/filters/amount")
    public List<Insurance> readAllPolicyAmount(@Valid @RequestParam double start, @RequestParam double end) {
        return service.filterPremiumAmount(start,end);
    }

    @PostMapping("/filters/types")
    public List<Insurance> readAllTypes(@RequestParam String key){
        return service.filterPolicyType(key);
    }
}
