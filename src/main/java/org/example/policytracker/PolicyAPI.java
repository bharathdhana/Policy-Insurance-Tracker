package org.example.policytracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/api")
public class PolicyAPI {
    @Autowired
    private PolicyService service;

    //To view the Home page
    @GetMapping("/new")
    public String newPolicy(Model model){
        Insurance ins = new Insurance();
        model.addAttribute("insurance",ins);
        return "introduce";
    }

    //To Get Data from the user
    @PostMapping("/open")
    public String openPolicy(Insurance insurance, Model model){
        System.out.println("insurance:"+insurance);
        //saving the attributes
        Insurance receive = service.ImplementSave(insurance);
        return "redirect:/new";
    }

    // To view the stored data from the database
    @GetMapping("/view")
    public String viewPolicy(Model model){
        List<Insurance> obj = service.ReadEverything();
        model.addAttribute("readpolicy",obj);
        return "view";
    }
    // To edit the Stored data via PolicyID
    @GetMapping("/edit/{key}")
    public String editPolicy(Model model, @PathVariable int key){
        Optional<Insurance> receive = service.EditElementById(key);
        if(receive.isPresent()){
            model.addAttribute("OldValue",receive.get());
            return "update";
        }
        return "redirect:/view";
    }

    //To Update the Data using Updateform Submission
    @PostMapping("/edit/{key}")
    //Data binding and send messages
    public String PolicyUpdate(@PathVariable int key, @ModelAttribute("OldValue") Insurance insurance, RedirectAttributes redirectAttributes){
        try{
            //fetch the PolicyId to overwrite existing data
            insurance.setPolicyId(key);
            //try to resave the updated attributes
            service.ImplementSave(insurance);
            //message to display
            redirectAttributes.addFlashAttribute("message","Policy updated successfully");
            return "redirect:/view";
        } catch(Exception e){
            //Exception Handling
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/edit" + key;
        }
    }
    @GetMapping("/delete/{key}")
    public String DeletePolicy(@PathVariable int key,RedirectAttributes redirectAttributes){
            try{
                //delete the data using PolicyId as key
                service.ImplementDelete(key);
                redirectAttributes.addFlashAttribute("message","Policy deleted successfully");
            } catch (Exception e){
                //Exception Handling
                redirectAttributes.addFlashAttribute("message",e.getMessage());
            }
            return "redirect:/view";
    }
}
