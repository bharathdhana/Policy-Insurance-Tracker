package org.example.policytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PolicyAPI {

    @Autowired
    private PolicyService service;

    @GetMapping("/new")
    public String newPolicy(Model model){
        model.addAttribute("insurance", new Insurance());
        return "introduce";
    }

    @PostMapping("/open")
    public String openPolicy(@ModelAttribute("insurance") Insurance insurance, RedirectAttributes redirectAttributes){
        service.ImplementSave(insurance);
        redirectAttributes.addFlashAttribute("message", "Policy created successfully!");
        return "redirect:/api/view";
    }

    @GetMapping("/view")
    public String viewPolicy(Model model){
        List<Insurance> obj = service.ReadEverything();
        model.addAttribute("readpolicy", obj);
        return "view";
    }

    @GetMapping("/edit/{key}")
    public String editPolicy(@PathVariable int key, Model model){
        Optional<Insurance> receive = service.EditElementById(key);
        if(receive.isPresent()){
            model.addAttribute("OldValue", receive.get());
            return "update";
        }
        return "redirect:/api/view";
    }

    @PostMapping("/edit/{key}")
    public String PolicyUpdate(@PathVariable int key,
                               @ModelAttribute("OldValue") Insurance insurance,
                               RedirectAttributes redirectAttributes){
        try{
            insurance.setPolicyId(key);
            service.ImplementSave(insurance);
            redirectAttributes.addFlashAttribute("message","Policy updated successfully");
            return "redirect:/api/view";
        } catch(Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/api/edit/" + key;
        }
    }

    @GetMapping("/delete/{key}")
    public String DeletePolicy(@PathVariable int key, RedirectAttributes redirectAttributes){
        try{
            service.ImplementDelete(key);
            redirectAttributes.addFlashAttribute("message","Policy deleted successfully");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/api/view";
    }

    @GetMapping("/filter-page")
    public String filterPage() {
        return "filter"; // filter.html
    }

    @GetMapping("/filter/types")
    public String filterByType(@RequestParam("category") String policyType, Model model) {
        List<Insurance> filteredPolicies;
        if (policyType == null || policyType.isEmpty()) {
            filteredPolicies = service.ReadEverything(); // show all if empty
        } else {
            filteredPolicies = service.filterPolicyType(policyType);
        }
        model.addAttribute("readpolicy", filteredPolicies);
        return "view";
    }

    @GetMapping("/filter/amount")
    public String filterByAmount(@RequestParam("start") Double start, @RequestParam("end") Double end, Model model) {
        List<Insurance> filteredPolicies;
        if (start == null || end == null || start > end) {
            filteredPolicies = service.ReadEverything(); // show all if invalid
        } else {
            filteredPolicies = service.filterPremiumAmount(start, end);
        }
        model.addAttribute("readpolicy", filteredPolicies);
        return "view";
    }

}
