package org.example.policytracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//Creating Entity Class
@Entity
public class Insurance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policyId;
    private long policy_number;
    private String policyholder_name;
    private String policy_type;
    private double premium_amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private String status;

    //Constructors
    public Insurance() {
    }

    public Insurance(int policyId, long policy_number, String policyholder_name, String policy_type, double premium_amount, LocalDate start_date, LocalDate end_date, String status) {
        this.policyId = policyId;
        this.policy_number = policy_number;
        this.policyholder_name = policyholder_name;
        this.policy_type = policy_type;
        this.premium_amount = premium_amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    //Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public long getPolicy_number() {
        return policy_number;
    }

    public void setPolicy_number(long policy_number) {
        this.policy_number = policy_number;
    }

    public String getPolicyholder_name() {
        return policyholder_name;
    }

    public void setPolicyholder_name(String policyholder_name) {
        this.policyholder_name = policyholder_name;
    }

    public String getPolicy_type() {
        return policy_type;
    }

    public void setPolicy_type(String policy_type) {
        this.policy_type = policy_type;
    }

    public double getPremium_amount() {
        return premium_amount;
    }

    public void setPremium_amount(double premium_amount) {
        this.premium_amount = premium_amount;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
