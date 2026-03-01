package org.example.policytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/")
    public String home(Model model) {
        List<Insurance> allPolicies = policyService.ReadEverything();

        long totalPolicies = allPolicies.size();
        double totalPremium = allPolicies.stream()
                .mapToDouble(Insurance::getPremiumAmount)
                .sum();

        long activePolicies = allPolicies.stream()
                .filter(p -> "Active".equalsIgnoreCase(p.getStatus()))
                .count();

        model.addAttribute("totalPolicies", totalPolicies);
        model.addAttribute("totalPremium", totalPremium);
        model.addAttribute("activePolicies", activePolicies);

        return "index";
    }
}
