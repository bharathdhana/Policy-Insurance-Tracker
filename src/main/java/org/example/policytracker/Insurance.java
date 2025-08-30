package org.example.policytracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//Creating Entity Class
@Entity
public class Insurance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policyId;
    private long policyNumber;
    @NotNull(message = "Policy Number is Mandatory")
    @Pattern(regexp = "^[A-Za-z ]{3,}$",message = "Invalid PolicyHolderName")
    private String policyHolderName;
    @Pattern(regexp = "^[A-Za-z ]{3,}$",message = "Invalid PolicyType")
    private String policyType;
    @NotNull(message = "Premium Amount is Mandatory")
    @Min(value = 1, message = "Invalid Premium Amount")
    private double premiumAmount;
    @Min(value = 1, message = "Invalid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Min(value = 1, message = "Invalid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull(message = "Invalid Status")
    private String status;

    //Constructors
    public Insurance() {
    }

    public Insurance(int policyId, long policyNumber, String policyHolderName, String policyType, double premiumAmount, LocalDate startDate, LocalDate endDate, String status) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.policyHolderName = policyHolderName;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    //Getters and Setters
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public long getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
