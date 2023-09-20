package com.example.customerrewardproject.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Customer {
    private Long id;
    @NotBlank
    private String name;
    private Double rewardPoints;
    @Email
    private String email;

}
